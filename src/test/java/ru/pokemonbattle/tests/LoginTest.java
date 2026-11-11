package ru.pokemonbattle.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.pokemonbattle.TestData;
import ru.pokemonbattle.framework.BaseTest;
import ru.pokemonbattle.pages.LoginPage;
import ru.pokemonbattle.pages.MainPage;


import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Успешная авторизация и проверка отображения ID тренера")
    public void testSuccessfulLogin() {
        MainPage mainPage = new LoginPage(driver)
                .open()
                .loginAs(TestData.LOGIN,TestData.PASSWORD);

        assertThat(mainPage.trainer_id())
                .as("ID тренера")
                .isEqualTo(TestData.EXPECTED_TRAINER_ID);
    }
}
