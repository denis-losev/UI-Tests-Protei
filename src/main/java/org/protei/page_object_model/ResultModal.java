package org.protei.page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResultModal {
    private final WebDriver webDriver;
    private final By modalTitle = By.xpath("//div[@class='uk-margin uk-modal-content']"),
            modalCloseButton = By.xpath("//button[@class='uk-button uk-button-primary uk-modal-close']"),
            invalidDataNotification = By.xpath("//div[@id='dataAlertsHolder']");

    public ResultModal(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getModalTitle() {
        return webDriver.findElement(modalTitle).getText();
    }

    public void clickModalCloseButton() {
        //new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(modalCloseButton));
        webDriver.findElement(modalCloseButton).click();
    }

    public String getErrorNotificationText() {
        return webDriver.findElement(invalidDataNotification).getText();
    }

    public MainAppPage closeModal() {
        clickModalCloseButton();
        return new MainAppPage(webDriver);
    }
}
