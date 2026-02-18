package de.cartok.quarkus.tutorial.backoffice.api;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;

@QuarkusTest
class CategoryResourceTest {

  // TODO: At least in this example /categories is defined to be only accessible by "admin" role. Should create an additional test which validates error case.
  @Test
  @TestSecurity(user = "bob", roles = {"admin"})
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
