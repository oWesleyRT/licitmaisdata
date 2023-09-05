package br.com.codewr.lmdados.service;

import br.com.codewr.lmdados.client.LicitMaisGetAllCompaniesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicitmaisService {

    @Autowired
    private LicitMaisGetAllCompaniesClient licitMaisGetAllCompaniesClient;

    @Autowired
    private LoginService loginService;

    public String login(){
        return loginService.login();
    }

    public String getAllCompanies(){
        String descricao = "caneta";
        Integer periodo = 3;
        String cookie = login();
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36";
        return licitMaisGetAllCompaniesClient.getAllCompanies(descricao, periodo, cookie, userAgent);
    }

}
