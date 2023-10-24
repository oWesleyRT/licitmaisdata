package br.com.codewr.lmdados.dto.out;

import br.com.codewr.lmdados.dto.in.SpecificCompanyDTOIn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDtoOut {

    private String cnpjVencedor;
    private String nomeVencedor;
    private String porte;
    private String email;
    private String fone;
    private String foneAlt;
    private String municipio;
    private String uf;

    public static CompanyDtoOut convertToDtoOut(
            ItenDTOOut itenDTO,
            SpecificCompanyDTOIn specificCompanyDTOIn
    ) {
        CompanyDtoOut dtoOut = new CompanyDtoOut();
        dtoOut.setCnpjVencedor(itenDTO.getCnpjVencedor());
        dtoOut.setNomeVencedor(itenDTO.getNomeVencedor());
        dtoOut.setPorte(specificCompanyDTOIn.getSize());
        dtoOut.setEmail(specificCompanyDTOIn.getEmail());
        dtoOut.setFone(specificCompanyDTOIn.getPhone());
        dtoOut.setFoneAlt(specificCompanyDTOIn.getPhoneAlt());
        if(specificCompanyDTOIn.getAddress() != null) {
            dtoOut.setMunicipio(specificCompanyDTOIn.getAddress().getCity());
            dtoOut.setUf(specificCompanyDTOIn.getAddress().getState());
        }
        return dtoOut;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getFoneAlt() {
        return foneAlt;
    }

    public void setFoneAlt(String foneAlt) {
        this.foneAlt = foneAlt;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
