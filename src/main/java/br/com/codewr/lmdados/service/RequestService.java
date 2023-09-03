package br.com.codewr.lmdados.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class RequestService {

    public String requestGeneralList() {
        try {
            String baseUrl = "https://www.sislicbr.licitmais.com.br/modulos/util/anam/dados.php";
            URI uri = URI.create(UriComponentsBuilder.fromUriString(baseUrl)
                    .queryParam("descricao", "caneta")
                    .queryParam("periodo", "3")
                    .toUriString());

            String cookie = "_gcl_au=1.1.2034177271.1692923729; _fbp=fb.2.1692923729754.788489362; _hjSessionUser_3545205=eyJpZCI6IjJkYTJlNDZmLTA2YWItNWNkZi1hYmJkLTIyMTZjYTk3ZGUyZCIsImNyZWF0ZWQiOjE2OTI5MjM3Mjk1MzUsImV4aXN0aW5nIjp0cnVlfQ==; _ga_RKN4F5GBH9=GS1.3.1693007192.3.1.1693008986.60.0.0; PHPSESSID=3ce95a42252a6985553ce15f9567fccd; _gid=GA1.3.1564235652.1693668924; ln_or=eyI1MDc2MDk4IjoiZCJ9; _hjIncludedInSessionSample_3545205=0; _hjSession_3545205=eyJpZCI6ImU3NGJmOThhLWE1MjItNDUyNS1iZDdmLTBkYmE5ODVmMTA0OCIsImNyZWF0ZWQiOjE2OTM2OTI5NDcyOTAsImluU2FtcGxlIjpmYWxzZX0=; _hjAbsoluteSessionInProgress=0; _ga=GA1.1.1947863499.1692923729; _ga_ZH4E9KLLW2=GS1.1.1693692946.13.1.1693693037.60.0.0";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Cookie", cookie)
                    .header(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            Gson gson = new GsonBuilder().create();
            //EnderecoDtoValidado enderecoDtoValidado = gson.fromJson(json, EnderecoDtoValidado.class);

            return json;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Requisition failed: " + e.getMessage());
        }
    }

}
