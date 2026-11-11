package ru.pokemonbattle.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{
    private final By trainerIdLocator = By.cssSelector(".header_card_trainer_id_num");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String trainer_id() {
        return getTextWhenVisible(trainerIdLocator);
    }
}
