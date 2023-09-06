package br.com.codewr.lmdados.service;

import br.com.codewr.lmdados.client.LicitMaisGetAllCompaniesClient;
import br.com.codewr.lmdados.dto.Itens;
import br.com.codewr.lmdados.dto.Test;
import br.com.codewr.lmdados.entity.CookieData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class LicitmaisService {

    private String cookiePath = "src/main/resources/cookie.json";

    @Autowired
    private LicitMaisGetAllCompaniesClient licitMaisGetAllCompaniesClient;

    @Autowired
    private LoginService loginService;

    public void login(){
        loginService.login();
    }

    public List<Itens> getAllCompanies(){
        String descricao = "caneta";
        Integer periodo = 3;
        CookieData cookie = generateCookie();
        String cookieContent = cookie.getCookie();
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36";
        String responseString = licitMaisGetAllCompaniesClient.getAllCompanies(descricao, periodo, cookieContent, userAgent);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Test response = objectMapper.readValue(responseString, Test.class);
            List<Itens> itensList = response.getItens();
            return itensList;
        } catch(JsonMappingException e) {
            throw new RuntimeException(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public CookieData generateCookie() {
        CookieData cookie = getCookieFromArchive();
        if(CookieData.cookieIsValid(cookie)) {
            return cookie;
        }
        loginService.login();
        return getCookieFromArchive();
    }

    public CookieData getCookieFromArchive() {
        try (FileReader reader = new FileReader(cookiePath)) {
            Gson gson = new Gson();
            CookieData cookieData = gson.fromJson(reader, CookieData.class);
            return cookieData;
        } catch (IOException e) {
            throw new RuntimeException("Failed to take cookie from the archive: " + e.getMessage());
        }
    }
}
