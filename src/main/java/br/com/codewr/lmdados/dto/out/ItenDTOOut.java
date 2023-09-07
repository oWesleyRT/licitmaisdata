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
}
