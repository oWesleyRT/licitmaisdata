package br.com.codewr.lmdados.service;

import br.com.codewr.lmdados.entity.CookieData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class LoginService {

    @Value("${url.login}")
    private String url;

    @Value("${user.login}")
    private String userLogin;

    @Value("${user.password}")
    private String userPassword;

    public void login() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        WebDriver browser = new ChromeDriver(options);
        browser.manage().window().maximize();

        try {
            navigateLoginPage(browser);
            fillForm(browser);
            clickEnter(browser);
            String allCookies = getAllCookies(browser);
            browser.quit();
            saveCookie(allCookies);
        } catch (Exception e) {
            throw new RuntimeException("Fail to obtain the cookie: " + e.getMessage());
        }
    }

    private void navigateLoginPage(WebDriver browser) {
        browser.navigate().to(url);
    }

    private void fillForm(WebDriver browser) {
        browser.findElement(By.name("data[login]")).sendKeys(userLogin);
        browser.findElement(By.name("data[senha]")).sendKeys(userPassword);
    }

    private void clickEnter(WebDriver browser) {
        browser.findElement(By.xpath("/html/body/div[2]/div/div[2]/fieldset/form/div[3]/div[1]/button")).click();
    }

    private String getAllCookies(WebDriver browser){
        Set<Cookie> cookies = browser.manage().getCookies();
        StringBuilder allCookies = new StringBuilder();
        for(Cookie cookie : cookies) {
            allCookies.append(cookie.getName())
                    .append("=")
                    .append(cookie.getValue())
                    .append("; ");
        }
        return allCookies.toString();
    }

    private void saveCookie(String cookie) {
        CookieData cookieData = new CookieData(cookie);

        String path = "src/main/resources/cookie.json";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(cookieData);

        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            throw new RuntimeException("Fail to create cookie archive");
        }
    }

}
