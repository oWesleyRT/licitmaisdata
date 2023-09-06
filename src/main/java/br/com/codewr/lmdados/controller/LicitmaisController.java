package br.com.codewr.lmdados.controller;

import br.com.codewr.lmdados.dto.Itens;
import br.com.codewr.lmdados.service.LicitmaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lmdados")
public class LicitmaisController {

    @Autowired
    private LicitmaisService licitmaisService;

    @GetMapping
    public List<Itens> csvData(){
        return licitmaisService.getAllCompanies();
    }

}
