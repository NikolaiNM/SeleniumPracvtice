package ru.pokemonbattle.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.pokemonbattle.framework.Config;

/**
 * Страница входа (/login)
 */
public class LoginPage extends BasePage{
    private final By emailInput = By.cssSelector("input[name='email']");
    private final By passwordInput = By.cssSelector("input[name='password']");
    private final By submitButton = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(Config.getUrl("/login"));
        return this;
    }

    public MainPage loginAs(String login, String password) {
        sendKey(emailInput, login);
        sendKey(passwordInput, password);
        clickWhenReady(submitButton);

        return new MainPage(driver);
    }

    public void sendKey(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }
}
