package utils;

import java.util.UUID;

public class TestDataFactory {

    private static int counter = 0;

    public static String getUniqueUserEmail() {
        return "user_" + System.currentTimeMillis() + "_" + (counter++) + "@test.com";
    }

    public static String getOrderBody(String userEmail) {
        return String.format("""
            {
                "userId": "%s",
                "email": "%s",
                "items": [
                    {"productId": 100, "quantity": 2}
                ],
                "total": 500
            }
            """, UUID.randomUUID().toString(), userEmail);
    }
}