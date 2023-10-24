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

    public String getCnpjVencedor() {
        return cnpjVencedor;
    }

    public void setCnpjVencedor(String cnpjVencedor) {
        this.cnpjVencedor = cnpjVencedor;
    }

    public String getNomeVencedor() {
        return nomeVencedor;
    }

    public void setNomeVencedor(String nomeVencedor) {
        this.nomeVencedor = nomeVencedor;
    }

    public String getOrgaoLicitante() {
        return orgaoLicitante;
    }

    public void setOrgaoLicitante(String orgaoLicitante) {
        this.orgaoLicitante = orgaoLicitante;
    }

    public String getNomeUg() {
        return nomeUg;
    }

    public void setNomeUg(String nomeUg) {
        this.nomeUg = nomeUg;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getModalidadeCompra() {
        return modalidadeCompra;
    }

    public void setModalidadeCompra(String modalidadeCompra) {
        this.modalidadeCompra = modalidadeCompra;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public ItensDateResultDTO getDataResultadoCompra() {
        return dataResultadoCompra;
    }

    public void setDataResultadoCompra(ItensDateResultDTO dataResultadoCompra) {
        this.dataResultadoCompra = dataResultadoCompra;
    }

    public Double getValorLicitacao() {
        return valorLicitacao;
    }

    public void setValorLicitacao(Double valorLicitacao) {
        this.valorLicitacao = valorLicitacao;
    }

    public String getPortal() {
        return portal;
    }

    public void setPortal(String portal) {
        this.portal = portal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
