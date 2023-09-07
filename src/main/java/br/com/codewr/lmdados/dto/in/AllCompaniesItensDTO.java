package br.com.codewr.lmdados.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllCompaniesItensDTO {

    @JsonProperty("CNPJVENCEDOR")
    private String cnpjVencedor;
    @JsonProperty("NOMEVENCEDOR")
    private String nomeVencedor;
    @JsonProperty("ORGAO")
    private String orgaoLicitante;
    @JsonProperty("NOMEUG")
    private String nomeUg;
    @JsonProperty("UF")
    private String uf;
    @JsonProperty("MUNICIPIO")
    private String municipio;
    @JsonProperty("MODALIDADECOMPRA")
    private String modalidadeCompra;
    @JsonProperty("OBJETO")
    private String objeto;
    @JsonProperty("DATARESULTADOCOMPRA")
    private ItensDateResultDTO dataResultadoCompra;
    @JsonProperty("VALORLICITACAO")
    private Double valorLicitacao;
    @JsonProperty("PORTAL")
    private String portal;
    @JsonProperty("DESCRICAO")
    private String descricao;
    @JsonProperty("MARCA")
    private String marca;
    @JsonProperty("FABRICANTE")
    private String fabricante;
    @JsonProperty("MODELO")
    private String modelo;

}
