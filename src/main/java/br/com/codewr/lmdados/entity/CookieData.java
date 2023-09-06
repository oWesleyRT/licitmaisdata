package br.com.codewr.lmdados.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CookieData {

    private String cookie;
    private String dateCreate;
    private String dateExpire;

    public CookieData(String cookie){
        LocalDateTime localDateTime = LocalDateTime.now();
        this.cookie = cookie;
        this.dateCreate = localDateTime.toString();
        this.dateExpire = localDateTime.plusHours(12).toString();
    }

    public static Boolean cookieIsValid(CookieData cookie){
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime dateTimeExpire = LocalDateTime.parse(cookie.getDateExpire());
        if(localDateTime.isBefore(dateTimeExpire)) {
            return true;
        }
        return false;
    }

}
