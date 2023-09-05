package br.com.codewr.lmdados.service;

import br.com.codewr.lmdados.client.LicitMaisGetAllCompaniesClient;
import br.com.codewr.lmdados.entity.CookieData;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;

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

    public String getAllCompanies(){
        String descricao = "caneta";
        Integer periodo = 3;
        CookieData cookie = generateCookie();
        String cookieContent = cookie.getCookie();
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36";
        return licitMaisGetAllCompaniesClient.getAllCompanies(descricao, periodo, cookieContent, userAgent);
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
