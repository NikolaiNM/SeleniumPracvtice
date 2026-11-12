package ru.pokemonbattle.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{
    private static final By TRAINER_ID = By.cssSelector(".header_card_trainer_id_num");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String trainerId() {
        return getText(TRAINER_ID);
    }
}
