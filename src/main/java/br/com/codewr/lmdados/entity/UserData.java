package br.com.codewr.lmdados.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class UserData {

    private Map<String, String> userInfos;

}
