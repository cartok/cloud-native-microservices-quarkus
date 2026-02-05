package de.cartok.quarkus.tutorial.backoffice.api;

import java.util.List;

import de.cartok.quarkus.tutorial.backoffice.api.model.Category;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.ws.rs.core.Response;

@NonBlocking
public class CategoriesApiImpl implements CategoriesApi {
  @Override
  public Response deleteCategory(String categoryId) {
    return null;
  }

  @Override
  public Response getCategories() {
    return Response.ok(List.of(new Category().name("drinks!"))).build();
  }

  @Override
  public Response getCategory(String categoryId) {
    return null;
  }

  @Override
  public Response postCategory(Category category) {
    return null;
  }

  @Override
  public Response putCategory(String categoryId, Category category) {
    return null;
  }
}
