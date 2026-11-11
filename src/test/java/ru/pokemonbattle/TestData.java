package ru.pokemonbattle;

import ru.pokemonbattle.framework.Config;

public class TestData {
    public static final String BASE_URL = Config.getUrl("/");

    public static final String LOGIN =  Config.getRequired("test.login");
    public static final String PASSWORD =  Config.getRequired("test.password");
    public static final String EXPECTED_TRAINER_ID = Config.getRequired("test.trainer_id");
}
