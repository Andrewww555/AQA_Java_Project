package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HealthCheckTest {

    private static final String BASE_URL = "http://localhost:8080";

    @Test
    @DisplayName("GET /health — сервис должен вернуть статус OK")
    void healthCheck_shouldReturnOk() {
        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when()
                .get("/health")
                .then()
                .statusCode(200)
                .body("status", equalTo("ok"));
    }
}