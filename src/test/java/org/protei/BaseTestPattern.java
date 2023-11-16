package org.protei;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTestPattern {
    protected WebDriver webDriver;
    public static WebDriver getWebDriver(String browser){
        switch (browser){
            case "Chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                System.setProperty("webdriver.chrome.driver", "src/test/java/org/protei/web_driver/chromedriver");
                return new ChromeDriver(options);
            case "Firefox":
                System.setProperty("webdriver.gecko.driver", "src/test/java/org/protei/web_driver/geckodriver");
                return new FirefoxDriver();
            default:
                throw new RuntimeException("Error: WebDriver not found");
        }
    }
    @Before
    public void startWebDriver() {
        webDriver = getWebDriver("Firefox");
        webDriver.get("file:///Users//xden15ka359x//IdeaProjects//UI-Tests-Protei//qa-test.html");
    }

    @After
    public void closeWebDriver() {
        webDriver.quit();
    }
}
