package br.com.codewr.lmdados.dto.in;

import br.com.codewr.lmdados.dto.all.Topico;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GptCategoriesDTOIn {

        @JsonProperty("codigo")
        Integer codigo;
        @JsonProperty("nome")
        String nome;
        @JsonProperty("palavra")
        String palavra;
        @JsonProperty("topicos")
        ArrayList<Topico> topicos;
}
