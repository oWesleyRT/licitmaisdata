package br.com.codewr.lmdados.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItensDateLongDTO {

    @JsonProperty("$numberLong")
    private Long dateNumber;

}
