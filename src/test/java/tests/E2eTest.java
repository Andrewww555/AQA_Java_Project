package tests;

import com.codeborne.selenide.Condition;
import config.UiTestConfig;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class E2eTest extends UiTestConfig {

    @Test
    @DisplayName("Полный путь пользователя: логин → выбор товара → корзина → оформление")
    void fullUserJourney() {
        openLoginPage();
        enterUsername("standard_user");
        enterPassword("secret_sauce");
        clickLoginButton();
        verifyLoginSuccess("Swag Labs");

        addToCart("sauce-labs-backpack");
        goToCart();
        verifyCartHasItem();
        clickCheckout();
        fillCheckoutForm("Test", "User", "12345");
        verifySummaryPage();
        finishPurchase();
        verifyOrderComplete("Thank you for your order!");
    }

    // Шаги логина
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

    // Шаги корзины и покупки
    @Step("Добавить товар: {productId}")
    private void addToCart(String productId) {
        $("#add-to-cart-" + productId).click();
    }

    @Step("Перейти в корзину")
    private void goToCart() {
        $(".shopping_cart_link").click();
    }

    @Step("Проверить, что товар в корзине")
    private void verifyCartHasItem() {
        $(".cart_item").shouldBe(Condition.visible);
    }

    @Step("Нажать Checkout")
    private void clickCheckout() {
        $("#checkout").click();
    }

    @Step("Заполнить форму: {firstName} {lastName}, {postalCode}")
    private void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        $("#first-name").setValue(firstName);
        $("#last-name").setValue(lastName);
        $("#postal-code").setValue(postalCode);
        $("#continue").click();
    }

    @Step("Проверить страницу подтверждения")
    private void verifySummaryPage() {
        $(".summary_total_label").shouldBe(Condition.visible);
    }

    @Step("Завершить покупку")
    private void finishPurchase() {
        $("#finish").click();
    }

    @Step("Проверить успешное завершение")
    private void verifyOrderComplete(String expectedMessage) {
        $(".complete-header").shouldHave(Condition.text(expectedMessage));
    }
}