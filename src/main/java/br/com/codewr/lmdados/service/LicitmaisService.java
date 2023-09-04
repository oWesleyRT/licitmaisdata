package br.com.codewr.lmdados.service;

import br.com.codewr.lmdados.client.LicitMaisGetAllCompaniesClient;
import br.com.codewr.lmdados.client.LicitMaisLoginClient;
import br.com.codewr.lmdados.entity.UserData;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LicitmaisService {

    @Autowired
    private LicitMaisGetAllCompaniesClient licitMaisGetAllCompaniesClient;

    @Autowired
    private LicitMaisLoginClient licitMaisLoginClient;

    public String login(){
//        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36";
//        MultiValueMap<String, String> object2 = new LinkedMultiValueMap<>();
//        object2.add("data[login]", "47271761000141");
//        object2.add("data[senha]", "Ecustomize3003");
//        ResponseEntity<String> retorno = licitMaisLoginClient.login(userAgent, object2);
//        List<String> cookie = retorno.getHeaders().get("Cookie");
//        System.out.println(cookie);

        try {
            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);

            String baseUrl = "https://www.sislicbr.licitmais.com.br/logar.php";
            String login = "47271761000141";
            String senha = "Ecustomize3003";
            String formData = "data[login]=" + login + "&data[senha]=" + senha;
            System.out.println(formData);
            URI uri = URI.create(UriComponentsBuilder.fromUriString(baseUrl)
                    .toUriString());

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(formData))
                    .header(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            CookieStore cookieStore = cookieManager.getCookieStore();
            List<HttpCookie> cookies = cookieStore.getCookies();
            StringBuilder cookieString = new StringBuilder();
            for(HttpCookie cookie : cookies){
                System.out.println(cookie);
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());
                cookieString.append(cookie.getName())
                        .append("=")
                        .append(cookie.getValue())
                        .append("; ");
            }

            System.out.println(cookieString.toString());

            String json = response.body();
            return cookieString.toString();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Requisition failed: " + e.getMessage());
        }
    }

    public String getAllCompanies(){
        String descricao = "caneta";
        Integer periodo = 3;
        String cookie = "_gcl_au=1.1.2034177271.1692923729; _fbp=fb.2.1692923729754.788489362; _hjSessionUser_3545205=eyJpZCI6IjJkYTJlNDZmLTA2YWItNWNkZi1hYmJkLTIyMTZjYTk3ZGUyZCIsImNyZWF0ZWQiOjE2OTI5MjM3Mjk1MzUsImV4aXN0aW5nIjp0cnVlfQ==; _ga_RKN4F5GBH9=GS1.3.1693007192.3.1.1693008986.60.0.0; PHPSESSID=3ce95a42252a6985553ce15f9567fccd; _gid=GA1.3.1564235652.1693668924; ln_or=eyI1MDc2MDk4IjoiZCJ9; _hjIncludedInSessionSample_3545205=0; _hjSession_3545205=eyJpZCI6ImU3NGJmOThhLWE1MjItNDUyNS1iZDdmLTBkYmE5ODVmMTA0OCIsImNyZWF0ZWQiOjE2OTM2OTI5NDcyOTAsImluU2FtcGxlIjpmYWxzZX0=; _hjAbsoluteSessionInProgress=0; _ga=GA1.1.1947863499.1692923729; _ga_ZH4E9KLLW2=GS1.1.1693692946.13.1.1693693037.60.0.0";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36";
        return licitMaisGetAllCompaniesClient.getAllCompanies(descricao, periodo, cookie, userAgent);
    }

}
