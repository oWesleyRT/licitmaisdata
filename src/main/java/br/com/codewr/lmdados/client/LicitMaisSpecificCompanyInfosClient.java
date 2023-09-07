package br.com.codewr.lmdados.client;

import br.com.codewr.lmdados.dto.in.SpecificCompanyDTOIn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="licitmaisSpecificCompanyClient", url = "${url.specificData}")
public interface LicitMaisSpecificCompanyInfosClient {

    @GetMapping
    String getSpecificCompany(
            @RequestParam String cnpj,
            @RequestParam Integer tp,
            @RequestHeader(name = "User-Agent") String userAgent
            );
}
