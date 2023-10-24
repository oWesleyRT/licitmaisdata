package br.com.codewr.lmdados.controller;

import br.com.codewr.lmdados.dto.in.GptCategoriesDTOIn;
import br.com.codewr.lmdados.service.GptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gpt")
public class GptController {

    @Autowired
    GptService gptService;

    @PostMapping("/categories")
    public ResponseEntity getCategoryByKeyword(@RequestBody GptCategoriesDTOIn gptCategoriesDTOIn) throws JsonProcessingException {
        return new ResponseEntity<>(gptService.resolve(gptCategoriesDTOIn), HttpStatus.OK);
    }
}
