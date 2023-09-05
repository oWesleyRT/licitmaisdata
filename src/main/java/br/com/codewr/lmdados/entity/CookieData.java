package br.com.codewr.lmdados.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CookieData {

    private String cookie;
    private Integer dayCreate;
    private Integer monthCreate;

    public CookieData(String cookie){
        LocalDate localDate = LocalDate.now();
        this.cookie = cookie;
        this.dayCreate = localDate.getDayOfMonth();
        this.monthCreate = localDate.getMonthValue();
    }

    public static Boolean cookieIsValid(CookieData cookie){
        LocalDate localDate = LocalDate.now();
        Integer dayToday = localDate.getDayOfMonth();
        Integer monthToday = localDate.getMonthValue();
        if(cookie.getDayCreate() == dayToday &&
                cookie.getMonthCreate() == monthToday) {
            return true;
        }
        return false;
    }

}
