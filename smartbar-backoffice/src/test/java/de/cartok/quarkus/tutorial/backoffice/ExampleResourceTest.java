package de.cartok.quarkus.tutorial.backoffice;

import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ExampleResourceTest {
  @Test
  void testHelloEndpoint() {
    given()
      .when().get("/backoffice")
      .then()
      .statusCode(200)
      .body(is("Hello from Quarkus REST"));
  }

}
