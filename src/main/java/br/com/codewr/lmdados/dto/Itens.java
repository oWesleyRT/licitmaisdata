package br.com.codewr.lmdados.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Itens {

    @JsonProperty("ORGAO")
    private String orgao;
    @JsonProperty("UF")
    private String uf;
    @JsonProperty("NOMEUG")
    private String nomeUg;
    @JsonProperty("VALORLICITACAO")
    private double valorLicitacao;
    @JsonProperty("CNPJVENCEDOR")
    private String cnpjVencedor;
    @JsonProperty("NOMEVENCEDOR")
    private String nomeVencedor;
    @JsonProperty("DESCRICAO")
    private String descricao;

}
