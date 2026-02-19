package de.cartok.quarkus.tutorial.backoffice.api;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class CategoryResourceTest {

  @Test
    //  @TestSecurity(user = "bob", roles = {"admin"})
  void getCategoriesAsAdminUser() {
    final var response = given()
      .when()
      .get("/categories")
      .then()
      .statusCode(200)
      .extract()
      .response()
      ;
    final var jsonPath = response.jsonPath();

    Assertions.assertEquals("Coffee", jsonPath.getString("[0].name"));
  }
}
