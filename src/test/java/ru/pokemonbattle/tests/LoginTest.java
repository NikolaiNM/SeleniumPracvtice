package ru.pokemonbattle.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pokemonbattle.TestData;
import ru.pokemonbattle.framework.BaseTest;
import ru.pokemonbattle.pages.LoginPage;
import ru.pokemonbattle.pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Успешная авторизация и проверка отображения ID тренера")
    public void showsTrainerIdAfterSuccessfulLogin() {
        MainPage mainPage = new LoginPage(driver)
                .open()
                .loginAs(TestData.EMAIL,TestData.PASSWORD);

        assertThat(mainPage.trainerId())
                .as("ID тренера")
                .isEqualTo(TestData.EXPECTED_TRAINER_ID);
    }
}
