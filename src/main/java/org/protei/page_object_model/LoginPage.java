package org.protei.page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver webDriver;
    private final By emailField = By.xpath("//input[@id='loginEmail']"),
            passwordField = By.xpath("//input[@id='loginPassword']"),
            loginButton = By.xpath("//button[@id='authButton']");

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillLoginForm(String email, String password) {
        webDriver.findElement(emailField).sendKeys(email);
        webDriver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        webDriver.findElement(loginButton).click();
    }

    public MainAppPage loginUser(String email, String password) {
        fillLoginForm(email, password);
        clickLoginButton();
        return new MainAppPage(webDriver);
    }
}
