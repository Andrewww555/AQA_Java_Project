package tests;

import com.codeborne.selenide.Condition;
import config.UiTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class LoginTest extends UiTestConfig {

    @Test
    @DisplayName("Успешный логин в SauceDemo")
    void successfulLogin() {
        open("https://www.saucedemo.com/");

        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce");
        $("#login-button").click();

        $(".app_logo").shouldHave(Condition.text("Swag Labs"));
    }
}