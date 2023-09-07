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
        dtoOut.setEmail(specificCompanyDTOIn.getEmail());
        dtoOut.setFone(specificCompanyDTOIn.getPhone());
        dtoOut.setFoneAlt(specificCompanyDTOIn.getPhoneAlt());
        dtoOut.setMunicipio(specificCompanyDTOIn.getAddress().getCity());
        dtoOut.setUf(specificCompanyDTOIn.getAddress().getState());
        return dtoOut;
    }

}
