package br.com.codewr.lmdados.controller;

import br.com.codewr.lmdados.dto.out.CompanyDtoOut;
import br.com.codewr.lmdados.dto.out.ItenDTOOut;
import br.com.codewr.lmdados.service.LicitmaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lmdados")
public class LicitmaisController {

    @Autowired
    private LicitmaisService licitmaisService;

    @GetMapping("/itens")
    public List<ItenDTOOut> itensCsvData(
            @RequestParam("description") String description,
            @RequestParam("period") Integer period
    ){
        return licitmaisService.generateItensList(description, period);
    }

    @GetMapping("/companies")
    public List<CompanyDtoOut> companiesCsvData(
            @RequestParam("description") String description,
            @RequestParam("period") Integer period
    ){
        return licitmaisService.generateCompaniesList(description, period);
    }

}
