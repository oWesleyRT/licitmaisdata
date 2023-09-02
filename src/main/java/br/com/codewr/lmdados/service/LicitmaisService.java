package br.com.codewr.lmdados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicitmaisService {

    @Autowired
    private RequestService requestService;

    public String csvData() {
        return requestService.requestGeneralList();
    }

}
