package tests;

import com.codeborne.selenide.Condition;
import config.UiTestConfig;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.*;

public class LoginTest extends UiTestConfig {

    @Test
    @DisplayName("Успешный логин в SauceDemo")
    void successfulLogin() {
        openLoginPage();
        enterUsername("standard_user");
        enterPassword("secret_sauce");
        clickLoginButton();
        verifyLoginSuccess("Swag Labs");
    }

    @ParameterizedTest(name = "{0} / {1}")
    @CsvSource({
            "standard_user, secret_sauce",
            "problem_user, secret_sauce",
            "performance_glitch_user, secret_sauce"
    })
    @DisplayName("Логин с разными пользователями")
    void loginWithDifferentUsers(String username, String password) {
        openLoginPage();
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        verifyLoginSuccess("Swag Labs");
    }

    @Step("Открыть страницу логина")
    private void openLoginPage() {
        open("https://www.saucedemo.com/");
    }

    @Step("Ввести имя пользователя: {username}")
    private void enterUsername(String username) {
        $("#user-name").setValue(username);
    }

    @Step("Ввести пароль")
    private void enterPassword(String password) {
        $("#password").setValue(password);
    }

    @Step("Нажать кнопку Login")
    private void clickLoginButton() {
        $("#login-button").click();
    }

    @Step("Проверить успешный вход")
    private void verifyLoginSuccess(String expectedText) {
        $(".app_logo").shouldHave(Condition.text(expectedText));
    }
}