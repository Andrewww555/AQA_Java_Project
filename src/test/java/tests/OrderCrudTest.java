package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OrderCrudTest {

    private static final String BASE_URL = "http://localhost:8080";

    @Test
    @DisplayName("GET /orders/123 — получение заказа")
    void getOrderById_shouldReturnOrder() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/orders/123")
                .then()
                .statusCode(200)
                .body("orderId", equalTo(123));
    }

    @Test
    @DisplayName("GET /orders — получение списка заказов")
    void getOrdersList_shouldReturnList() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/orders")
                .then()
                .statusCode(200)
                .body("orders", notNullValue());
    }

    @Test
    @DisplayName("PUT /orders/123 — обновление заказа")
    void updateOrder_shouldReturn200() {
        String updateBody = """
            {
                "status": "UPDATED"
            }
            """;

        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when()
                .put("/orders/123")
                .then()
                .statusCode(200)
                .body("status", equalTo("UPDATED"));
    }

    @Test
    @DisplayName("DELETE /orders/123 — удаление заказа")
    void deleteOrder_shouldReturn204() {
        given()
                .baseUri(BASE_URL)
                .when()
                .delete("/orders/123")
                .then()
                .statusCode(204);
    }
}