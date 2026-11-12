package ru.pokemonbattle.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.pokemonbattle.TestData;
import ru.pokemonbattle.framework.TestContext;
import ru.pokemonbattle.pages.LoginPage;
import ru.pokemonbattle.pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {
    private final TestContext context;
    private LoginPage loginPage;
    private MainPage mainPage;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @Given("нахожусь на странице входа")
    public void openLoginPage() {
        loginPage = new LoginPage(context.driver()).open();
    }

    @When("вхожу под своей учётной записью")
    public void loginWithValidCredentials() {
        mainPage = loginPage.loginAs(TestData.EMAIL, TestData.PASSWORD);
    }

    @Then("вижу ID своего тренера")
    public void trainerIdIsShown() {
        assertThat(mainPage.trainerId())
                .as("ID тренера")
                .isEqualTo(TestData.EXPECTED_TRAINER_ID);
    }

}
