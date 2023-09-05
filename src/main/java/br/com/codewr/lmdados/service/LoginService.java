package br.com.codewr.lmdados.service;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LoginService {

    @Value("${url.login}")
    private String url;

    public String login() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();

        try {
            navigateLoginPage(browser);
            fillForm(browser);
            clickEnter(browser);
            String allCookies = getAllCookies(browser);
            browser.quit();
            return allCookies;
        } catch (Exception e) {
            throw new RuntimeException("Fail to obtain the cookie");
        }
    }

    private void navigateLoginPage(WebDriver browser) {
        browser.navigate().to(url);
        threadSleep();
    }

    private void fillForm(WebDriver browser) {
        browser.findElement(By.name("data[login]")).sendKeys("47271761000141");
        browser.findElement(By.name("data[senha]")).sendKeys("Ecustomize3003");
        threadSleep();
    }

    private void clickEnter(WebDriver browser) {
        browser.findElement(By.xpath("/html/body/div[2]/div/div[2]/fieldset/form/div[3]/div[1]/button")).click();
        threadSleep();
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

    private void threadSleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread sleep failed");
        }
    }

}
