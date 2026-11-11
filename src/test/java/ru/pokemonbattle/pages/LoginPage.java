package ru.pokemonbattle.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.pokemonbattle.framework.Config;

/**
 * Страница входа (/login)
 */
public class LoginPage extends BasePage{
    private static final By EMAIL_INPUT = By.cssSelector("input[name='email']");
    private static final By PASSWORD_INPUT = By.cssSelector("input[name='password']");
    private static final By SUBMIT_BUTTON = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(Config.getUrl("/login"));
        return this;
    }

    public MainPage loginAs(String login, String password) {
        type(EMAIL_INPUT, login);
        type(PASSWORD_INPUT, password);
        click(SUBMIT_BUTTON);

        return new MainPage(driver);
    }
}
