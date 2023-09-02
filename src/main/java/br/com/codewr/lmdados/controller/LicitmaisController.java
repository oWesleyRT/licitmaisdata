package br.com.codewr.lmdados.controller;

import br.com.codewr.lmdados.service.LicitmaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lmdados")
public class LicitmaisController {

    @Autowired
    private LicitmaisService licitmaisService;

    @GetMapping
    public String csvData(){
        return licitmaisService.csvData();
    }

}
