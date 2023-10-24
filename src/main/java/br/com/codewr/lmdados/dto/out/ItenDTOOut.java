package br.com.codewr.lmdados.dto.out;

import br.com.codewr.lmdados.dto.in.AllCompaniesItensDTO;
import br.com.codewr.lmdados.dto.in.SpecificCompanyDTOIn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItenDTOOut {

    private String cnpjVencedor;
    private String nomeVencedor;
    private String orgaoLicitante;
    private String nomeUg;
    private String ufOrgao;
    private String municipioOrgao;
    private String modalidadeCompra;
    private String objeto;
    private String dataResultadoCompra;
    private Double valorLicitacao;
    private String portal;
    private String descricao;
    private String marca;
    private String fabricante;
    private String modelo;

    public static ItenDTOOut convertDTOIn(AllCompaniesItensDTO dtoIn) {
        ItenDTOOut dtoOut = new ItenDTOOut();
        dtoOut.setCnpjVencedor(dtoIn.getCnpjVencedor());
        dtoOut.setNomeVencedor(dtoIn.getNomeVencedor());
        dtoOut.setOrgaoLicitante(dtoIn.getOrgaoLicitante());
        dtoOut.setNomeUg(dtoIn.getNomeUg());
        dtoOut.setUfOrgao(dtoIn.getUf());
        dtoOut.setMunicipioOrgao(dtoIn.getMunicipio());
        dtoOut.setModalidadeCompra(dtoIn.getModalidadeCompra());
        dtoOut.setObjeto(dtoIn.getObjeto());
        dtoOut.setDataResultadoCompra(dtoOut.dateConverter(dtoIn.getDataResultadoCompra().getDateLong().getDateNumber()));
        dtoOut.setValorLicitacao(dtoIn.getValorLicitacao());
        dtoOut.setPortal(dtoIn.getPortal());
        dtoOut.setDescricao(dtoIn.getDescricao());
        dtoOut.setMarca(dtoIn.getMarca());
        dtoOut.setFabricante(dtoIn.getFabricante());
        dtoOut.setModelo(dtoIn.getModelo());
        return dtoOut;
    }

    public String dateConverter(Long number) {
        Instant instant = Instant.ofEpochMilli(number);
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ItenDTOOut that = (ItenDTOOut) obj;
        return Objects.equals(cnpjVencedor, that.cnpjVencedor);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cnpjVencedor);
    }

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

    public String getUfOrgao() {
        return ufOrgao;
    }

    public void setUfOrgao(String ufOrgao) {
        this.ufOrgao = ufOrgao;
    }

    public String getMunicipioOrgao() {
        return municipioOrgao;
    }

    public void setMunicipioOrgao(String municipioOrgao) {
        this.municipioOrgao = municipioOrgao;
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

    public String getDataResultadoCompra() {
        return dataResultadoCompra;
    }

    public void setDataResultadoCompra(String dataResultadoCompra) {
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
