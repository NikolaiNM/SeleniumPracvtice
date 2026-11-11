package ru.pokemonbattle.framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Централизованный менеджер конфигурации тестового фреймворка.
 * <p>
 * Читает настройки из файлов {@code config.properties} и {@code local.properties}
 * <p>
 * <b>Приоритет получения значений:</b>
 * <ol>
 *   <li>Системные свойства JVM </li>
 *   <li>Файлы свойств ({@code local.properties} перезаписывает значения из {@code config.properties})</li>
 *   <li>Значение по умолчанию (defaultValue), указанное в коде при вызове метода {@code get()}</li>
 * </ol>
 * <p>
 * <b>Примеры запуска тестов:</b>
 * <ul>
 *   <li>{@code mvn test} — запуск с настройками из файлов по умолчанию (prod, chrome)</li>
 *   <li>{@code mvn test -Denv=stage -Dbrowser=firefox} — запуск на стейдж-окружении в Firefox</li>
 *   <li>{@code mvn test -Dheadless=true} — запуск браузера в фоновом режиме</li>
 * </ul>
 */
public class Config {
    private static final Properties properties = new Properties();

    static {
        load("config.properties");
        load("local.properties");
    }

    /**
     * Загружает свойства из файла в объект Properties.
     *
     * @param filename имя файла в папке resources
     */
    private static void load(String filename) {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(filename)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
        }
    }

    /**
     * Получение значения конфигурации с учетом приоритетов.
     * <p>
     * Приоритет:
     * <ol>
     *  <li>Консоль (mvn test -Dheadless=true)</li>
     *  <li>Значение из файлов properties</li>
     *  <li> {@code defaultValue} из кода</li>
     * </ol>
     * @param key ключ настройки (например "browser" или test.login)
     * @param defaultValue значение по умолчанию, если ключ не найден
     * @return итоговое значение
     */
    public static String get(String key, String defaultValue) {
        String systemValue = System.getProperty(key);
        if (systemValue != null) {
            return systemValue;
        }

        String fileValue = properties.getProperty(key);
        if (fileValue != null) {
            return fileValue;
        }

        return defaultValue;
    }

    /**
     * Получение ОБЯЗАТЕЛЬНОГО значения конфигурации.
     * Если значение не найдено ни в системных свойствах, ни в файлах,
     * выбрасывает исключение с название проблемного поля.
     *
     * @param key ключ настройки (например, "test.login")
     * @return значение настройки
     * @throws IllegalStateException если настройка отсутствует
     */
    public static String getRequired(String key) {
        String value = get(key, null);

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalStateException(
                    String.format("Отсуствует настройка: '%s'. " +
                            "Добавте ее в local.properties или передайте при запуске: -D%s=ваше_значение", key, key)
            );
        }
        return value;
    }

    /**
     * Формирует полный URL на основе текущего окружения и относительного пути.
     *
     * @param path относительный путь (например "/login" или "login")
     * @return полный корректный URL (например "https://pokemonbattle.ru/login")
     */
    public static String getUrl(String path) {
        String envString = get("env", "prod");

        Environment env = Environment.fromString(envString);

        String cleanPath = path.startsWith("/") ? path : "/" + path;

        return env.getBaseUrl() + cleanPath;
    }
}
