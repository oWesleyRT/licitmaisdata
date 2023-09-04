package br.com.codewr.lmdados.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name="licitmaisLoginClient", url = "${url.login}")
public interface LicitMaisLoginClient {

    @PostMapping
    ResponseEntity<String> login(@RequestHeader(name = "User-Agent") String userAgent,
                                 @RequestBody MultiValueMap<String, String> params);



}
