package ru.pokemonbattle.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ru.pokemonbattle.framework.TestContext;

public class Hooks {
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setUp() {
        context.init();
    }

    @After
    public void tearDown() {
        context.quit();
    }
}
