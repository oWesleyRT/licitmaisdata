package br.com.codewr.lmdados.dto.in;

import br.com.codewr.lmdados.dto.in.ItensDateLongDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItensDateResultDTO {

    @JsonProperty("$date")
    private ItensDateLongDTO dateLong;

    public ItensDateLongDTO getDateLong() {
        return dateLong;
    }

    public void setDateLong(ItensDateLongDTO dateLong) {
        this.dateLong = dateLong;
    }
}
