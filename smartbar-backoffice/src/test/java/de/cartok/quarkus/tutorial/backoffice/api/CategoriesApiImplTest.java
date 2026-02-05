package de.cartok.quarkus.tutorial.backoffice.api;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.cartok.quarkus.tutorial.backoffice.CategoriesService;
import de.cartok.quarkus.tutorial.backoffice.api.model.Category;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class CategoriesApiImplTest {

  @InjectMock
  CategoriesService categoriesServiceMock;

  @BeforeEach
  void setUp() {
    Mockito.when(categoriesServiceMock.get()).thenReturn(new Category().name("drinks!"));
  }

  @Test
  void getCategories() {
    final var response = given()
      .when().get("/categories")
      .then()
      .statusCode(200)
      .extract().response();
    final var jsonPath = response.jsonPath();

    Assertions.assertEquals("drinks!", jsonPath.getString("[0].name"));
  }
}
