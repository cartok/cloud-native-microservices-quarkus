package de.cartok.quarkus.tutorial.backoffice.api;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import de.cartok.quarkus.tutorial.backoffice.category.CategoriesService;
import de.cartok.quarkus.tutorial.backoffice.category.CategoryEntity;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class CategoriesResourceTest {

  @InjectMock
  CategoriesService categoriesServiceMock;

  @BeforeEach
  void setUp() {
    final CategoryEntity category = new CategoryEntity();
    category.setName("drinks!");
    Mockito.when(categoriesServiceMock.listAll()).thenReturn(List.of(category));
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
