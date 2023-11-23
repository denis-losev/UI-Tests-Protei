package org.protei.page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class MainAppPage {
    private final WebDriver webDriver;
    private final By emailField = By.xpath("//input[@id='dataEmail']"),
            nameField = By.xpath("//input[@id='dataName']"),
            genderDropdown = By.xpath("//input[@id='dataName']"),
            addButton = By.xpath("//button[@id='dataSend']"),
            invalidDataNotification = By.xpath("//div[@id='authAlertsHolder']");

    public MainAppPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getAddButtonText() {
        return webDriver.findElement(addButton).getText();
    }

    public String getErrorNotificationText() {
        return webDriver.findElement(invalidDataNotification).getText();
    }

    public void selectGender(String gender) {
        int genderSelect = Objects.equals(gender, "Женский") ? 2 : 1;
        webDriver.findElement(genderDropdown).click();
        webDriver.findElement(By.xpath("//select[@id='dataGender']/option["+ genderSelect + "]")).click();
    }

    public void selectCheckbox(int option) {
        if (option == 12) {
            webDriver.findElement(By.xpath("//input[@id='dataCheck11']")).click();
            webDriver.findElement(By.xpath("//input[@id='dataCheck12']")).click();
        } else if (option == 1 || option == 2) {
            webDriver.findElement(By.xpath("//input[@id='dataCheck1" + option + "']")).click();
        }
    }

    public void selectRadio(int option) {
        if (option >= 1 && option <= 3) {
            webDriver.findElement(By.xpath("//input[@id='dataSelect2"+option+"']")).click();
        }
    }

    public String getTableData(int option) {
        return webDriver.findElement(By.xpath("//tbody/tr/td["+option+"]")).getText();
    }

    public ResultModal addUserData(String email, String name, String genderSelect, int checkBoxOptions, int radioSelect) {
        webDriver.findElement(emailField).sendKeys(email);
        webDriver.findElement(nameField).sendKeys(name);
        selectGender(genderSelect);
        selectCheckbox(checkBoxOptions);
        selectRadio(radioSelect);
        webDriver.findElement(addButton).click();
        return new ResultModal(webDriver);
    }

    public ResultModal fillRequireFields(String email, String name) {
        webDriver.findElement(emailField).sendKeys(email);
        webDriver.findElement(nameField).sendKeys(name);
        webDriver.findElement(addButton).click();
        return new ResultModal(webDriver);
    }
}
