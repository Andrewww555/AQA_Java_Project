package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OrderApiTest {

    private static final String BASE_URL = "http://localhost:8080";

    @Test
    @DisplayName("POST /orders — создание заказа")
    void createOrder_shouldReturn201() {
        String orderBody = """
            {
                "userId": 1,
                "items": [{"productId": 100, "quantity": 2}],
                "total": 500
            }
            """;

        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(orderBody)
                .when()
                .post("/orders")
                .then()
                .statusCode(201)
                .body("orderId", notNullValue())
                .body("status", equalTo("CREATED"));
    }
}