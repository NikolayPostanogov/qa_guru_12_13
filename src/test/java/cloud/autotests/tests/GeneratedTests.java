package cloud.autotests.tests;

import cloud.autotests.config.ProjectConfig;
import cloud.autotests.helpers.DriverUtils;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class GeneratedTests extends TestBase {
    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    String baseUrl = config.baseUrl();
    @Test
    @DisplayName("Проверка страницы услуг")
    void serviceCheck() {
        step("Открытие страницы '" + baseUrl + "'", () ->
                open(baseUrl));

        step("Нажатие Услуги", () ->
                $(byText("Услуги")).click());

        step("Проверка заголовка статьи", () -> {
            $(".head-cont").shouldHave(text("Услуги"));
        });
    }

    @ParameterizedTest(name = "Проверка страницы услуги {0}")
    @CsvSource(value = {
            "Фронт",
            "Миддл",
            "Бэк",
            "Импортозамещение",
            "Цифровые решения",
            "Аналитика",
            "BigData"
    })
    @DisplayName("Проверка страниц услуг")
    void servicesCheck(String service) {
        step("Открытие страницы '" + baseUrl + "'", () ->
                open(baseUrl));

        step("Нажатие Услуги", () ->
                $(byText("Услуги")).click());

        step("Нажатие " + service, () ->
                $(".b_block").$(byText(service)).click());

        step("Проверка заголовка статьи", () -> {
            $(".head-cont").shouldHave(text(service));
        });
    }

    @Test
    @DisplayName("Проверка заголовка страницы")
    void titleTest() {
        step("Открытие страницы '" + baseUrl + "'", () ->
                open(baseUrl));

        step("Проверка заголовка страницы '«Т1 Консалтинг»'", () -> {
            String expectedTitle = "«Т1 Консалтинг»";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @DisplayName("Проверка консоли на наличие ошибок")
    void consoleShouldNotHaveErrorsTest() {
        step("Открытие страницы '" + baseUrl + "'", () ->
                open(baseUrl));

        step("Проверка лога консоли", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}