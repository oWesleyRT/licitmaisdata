package br.com.codewr.lmdados.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="licitmaisClient", url = "${url.generalData}")
public interface LicitMaisGetAllCompaniesClient {

    @GetMapping
    String getAllCompanies(
            @RequestParam String descricao,
            @RequestParam Integer periodo,
            @RequestParam String estado,
            @RequestHeader(name = "Cookie") String cookie,
            @RequestHeader(name = "User-Agent") String userAgent
            );
}
