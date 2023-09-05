package br.com.codewr.lmdados.controller;

import br.com.codewr.lmdados.service.LicitmaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/lmdados")
public class LicitmaisController {

    @Autowired
    private LicitmaisService licitmaisService;

    @GetMapping
    public String csvData(){
        return licitmaisService.getAllCompanies();
    }

    @GetMapping("/login")
    public String login() {
        return licitmaisService.login();
    }

}
