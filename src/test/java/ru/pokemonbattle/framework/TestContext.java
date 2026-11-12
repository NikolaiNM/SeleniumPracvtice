package ru.pokemonbattle.framework;

import org.openqa.selenium.WebDriver;

public class TestContext {
    private WebDriver driver;

    public void init() {
        driver = BrowserFactory.createDriver();
    }

    public WebDriver driver() {
        return driver;
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
