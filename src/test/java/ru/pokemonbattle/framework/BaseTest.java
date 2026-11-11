package ru.pokemonbattle.framework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

/**
 * Базовый класс для тестов.
 * Управляет жизненным циклом WebDriver и предоставляет вспомогательные методы.
 */
public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = BrowserFactory.createDriver();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
