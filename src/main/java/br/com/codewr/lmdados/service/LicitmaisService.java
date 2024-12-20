package br.com.codewr.lmdados.service;

import br.com.codewr.lmdados.client.LicitMaisGetAllCompaniesClient;
import br.com.codewr.lmdados.client.LicitMaisSpecificCompanyInfosClient;
import br.com.codewr.lmdados.dto.in.AllCompaniesItensDTO;
import br.com.codewr.lmdados.dto.in.AllCompanies;
import br.com.codewr.lmdados.dto.in.SpecificCompanyDTOIn;
import br.com.codewr.lmdados.dto.out.CompanyDtoOut;
import br.com.codewr.lmdados.dto.out.ItenDTOOut;
import br.com.codewr.lmdados.entity.CookieData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class LicitmaisService {

    private String cookiePath = "src/main/resources/cookie.json";

    @Autowired
    private LicitMaisGetAllCompaniesClient licitMaisGetAllCompaniesClient;

    @Autowired
    private LicitMaisSpecificCompanyInfosClient licitMaisSpecificCompanyInfosClient;

    @Autowired
    private LoginService loginService;

    @Value("${user.agent}")
    private String userAgent;

    private CookieData generateCookie() {
        CookieData cookie = getCookieFromArchive();
        if(CookieData.cookieIsValid(cookie)) {
            return cookie;
        }
        loginService.login();
        return getCookieFromArchive();
    }

    private CookieData getCookieFromArchive() {
        try (FileReader reader = new FileReader(cookiePath)) {
            Gson gson = new Gson();
            CookieData cookieData = gson.fromJson(reader, CookieData.class);
            return cookieData;
        } catch (IOException e) {
            throw new RuntimeException("Failed to take cookie from the archive: " + e.getMessage());
        }
    }

    private List<ItenDTOOut> generateItens(String description, String uf, Integer period){
        String cookieContent = generateCookie().getCookie();
        String responseString = licitMaisGetAllCompaniesClient.getAllCompanies(description, period, uf, cookieContent, userAgent);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if(responseString.contains("<title>.:: Licitmais | Banco de dados de Licitações ::.</title>")) {
                loginService.login();
                cookieContent = generateCookie().getCookie();
                responseString = licitMaisGetAllCompaniesClient.getAllCompanies(description, period, uf, cookieContent, userAgent);
            }
            AllCompanies response = objectMapper.readValue(responseString, AllCompanies.class);
            List<AllCompaniesItensDTO> allCompaniesItensDTOList = response.getItens();
            List<ItenDTOOut> itenDTOOutList = allCompaniesItensDTOList
                    .stream()
                    .map((allCompaniesItensDTO -> ItenDTOOut.convertDTOIn(allCompaniesItensDTO)))
                    .toList();
            return itenDTOOutList;
        } catch(JsonMappingException e) {
            throw new RuntimeException(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

//    private List<CompanyDtoOut> generateCompanies(List<ItenDTOOut> itenDTOOutList) {
//        List<ItenDTOOut> itenList = itenDTOOutList.stream()
//                .distinct()
//                .collect(Collectors.toList());
//        List<CompanyDtoOut> companyDtoOutList = new ArrayList<>();
//        ObjectMapper objectMapper = new ObjectMapper();
//        AtomicReference<Integer> i = new AtomicReference<>(0);
//        itenList.forEach(
//                item -> {
//                    i.set(i.get() + 1);
//                    System.out.println("Fazendo requisição: " + i);
//                    String responseString = licitMaisSpecificCompanyInfosClient.getSpecificCompany(
//                            item.getCnpjVencedor(),
//                            2,
//                            userAgent);
//                    try {
//                        SpecificCompanyDTOIn specificCompanyDTOIn = objectMapper.readValue(responseString, SpecificCompanyDTOIn.class);
//                        companyDtoOutList.add(CompanyDtoOut.convertToDtoOut(item, specificCompanyDTOIn));
//                    } catch (JsonProcessingException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//        );
//        return companyDtoOutList;
//    }

    public List<CompanyDtoOut> generateCompanies(List<ItenDTOOut> itenDTOOutList) {
        List<ItenDTOOut> distinctItenList = itenDTOOutList.stream()
                .distinct()
                .toList();
        List<CompletableFuture<CompanyDtoOut>> futures = new ArrayList<>();
        AtomicInteger i = new AtomicInteger(0);
        for (ItenDTOOut item : distinctItenList) {
            i.incrementAndGet();
            System.out.println("Fazendo requisição: " + i);
            ObjectMapper objectMapper = new ObjectMapper();
            CompletableFuture<CompanyDtoOut> future = CompletableFuture.supplyAsync(() -> {
                String responseString = licitMaisSpecificCompanyInfosClient.getSpecificCompany(
                        item.getCnpjVencedor(),
                        2,
                        userAgent);
                try {
                    SpecificCompanyDTOIn specificCompanyDTOIn = objectMapper.readValue(responseString, SpecificCompanyDTOIn.class);
                    return CompanyDtoOut.convertToDtoOut(item, specificCompanyDTOIn);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
            futures.add(future);
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        List<CompanyDtoOut> companyDtoOutList = new ArrayList<>();
        allOf.thenRun(() -> {
            for (CompletableFuture<CompanyDtoOut> future : futures) {
                try {
                    companyDtoOutList.add(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }).join();

        return companyDtoOutList;
    }

    public List<ItenDTOOut> generateItensList(String description, String uf, Integer period) {
        List<ItenDTOOut> itenDTOOutList = generateItens(description, uf, period);
        return itenDTOOutList;
    }

    public List<CompanyDtoOut> generateCompaniesList(String description, String uf, Integer period) {
        List<ItenDTOOut> itenDTOOutList = generateItens(description, uf, period);
        List<CompanyDtoOut> companyDtoOutList = generateCompanies(itenDTOOutList);
        return companyDtoOutList;
    }
}
