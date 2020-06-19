package org.acme.rest.client.multipart;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class MultipartResourceTest {

    @Test
    public void testMultipartDataIsSent() {
        RestAssured
                .given()
                .when()
                    .post("/client/multipart")
                .then()
                    .statusCode(200)
                .body(containsString("Content-Disposition: form-data; name=\"file1\""),
                        containsString("Content-Disposition: form-data; name=\"file2\""),
                        containsString("the content"),
                        containsString("the second content"),
                        containsString("greeting, greeting2"));
    }

}
