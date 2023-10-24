package br.com.codewr.lmdados.dto.out;

import br.com.codewr.lmdados.dto.all.Topico;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GPTCategoriesDTOOut {

        @JsonProperty("topicos")
        List<Topico> topicos;
}
