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

    private List<ItenDTOOut> generateItens(String description, Integer period){
        String cookieContent = generateCookie().getCookie();
        String responseString = licitMaisGetAllCompaniesClient.getAllCompanies(description, period, cookieContent, userAgent);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
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

    private List<CompanyDtoOut> generateCompanies(List<ItenDTOOut> itenDTOOutList) {
        List<ItenDTOOut> itenList = itenDTOOutList.stream()
                .distinct()
                .collect(Collectors.toList());
        List<CompanyDtoOut> companyDtoOutList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        AtomicReference<Integer> i = new AtomicReference<>(0);
        itenList.forEach(
                item -> {
                i.set(i.get() + 1);
                System.out.println("Fazendo requisição: " + i);
                String responseString = licitMaisSpecificCompanyInfosClient.getSpecificCompany(
                        item.getCnpjVencedor(),
                        2,
                        userAgent);
                try {
                    SpecificCompanyDTOIn specificCompanyDTOIn = objectMapper.readValue(responseString, SpecificCompanyDTOIn.class);
                    companyDtoOutList.add(CompanyDtoOut.convertToDtoOut(item, specificCompanyDTOIn));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        );
        return companyDtoOutList;
    }

    public List<ItenDTOOut> generateItensList(String description, Integer period) {
        List<ItenDTOOut> itenDTOOutList = generateItens(description, period);
        return itenDTOOutList;
    }

    public List<CompanyDtoOut> generateCompaniesList(String description, Integer period) {
        List<ItenDTOOut> itenDTOOutList = generateItens(description, period);
        List<CompanyDtoOut> companyDtoOutList = generateCompanies(itenDTOOutList);
        return companyDtoOutList;
    }
}
