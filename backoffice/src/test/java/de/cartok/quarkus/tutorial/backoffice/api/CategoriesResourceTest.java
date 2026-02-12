package de.cartok.quarkus.tutorial.backoffice.api;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import de.cartok.quarkus.tutorial.backoffice.category.CategoryEntity;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class CategoriesResourceTest {

  @BeforeEach
  void setUp() {
    PanacheMock.mock(CategoryEntity.class);
    final CategoryEntity categoryEntity = new CategoryEntity();
    categoryEntity.name = "drinks!";
    Mockito.when(CategoryEntity.listAll()).thenReturn(List.of(categoryEntity));
  }

  @Test
  void getCategories() {
    final var response = given()
      .when().get("/categories")
      .then()
      .statusCode(200)
      .extract().response()
      ;
    final var jsonPath = response.jsonPath();

    Assertions.assertEquals("drinks!", jsonPath.getString("[0].name"));
  }
}
