package tests;

import com.codeborne.selenide.Condition;
import config.UiTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class E2eTest extends UiTestConfig {

    @Test
    @DisplayName("Полный путь пользователя: логин → выбор товара → корзина → оформление")
    void fullUserJourney() {
        // 1. Логин
        open("https://www.saucedemo.com/");
        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce");
        $("#login-button").click();

        // 2. Проверка, что мы на странице каталога
        $(".app_logo").shouldHave(Condition.text("Swag Labs"));

        // 3. Добавляем товар в корзину
        $("#add-to-cart-sauce-labs-backpack").click();

        // 4. Переходим в корзину
        $(".shopping_cart_link").click();

        // 5. Проверяем, что товар добавился
        $(".cart_item").shouldBe(Condition.visible);

        // 6. Нажимаем Checkout
        $("#checkout").click();

        // 7. Заполняем форму оформления
        $("#first-name").setValue("Test");
        $("#last-name").setValue("User");
        $("#postal-code").setValue("12345");
        $("#continue").click();

        // 8. Проверяем, что попали на страницу подтверждения
        $(".summary_total_label").shouldBe(Condition.visible);

        // 9. Завершаем покупку
        $("#finish").click();

        // 10. Проверяем успешное завершение
        $(".complete-header").shouldHave(Condition.text("Thank you for your order!"));
    }
}