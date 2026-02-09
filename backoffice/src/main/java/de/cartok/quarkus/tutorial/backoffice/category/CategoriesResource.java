package de.cartok.quarkus.tutorial.backoffice.category;

import java.util.List;

import de.cartok.quarkus.tutorial.backoffice.api.CategoriesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiCategory;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@NonBlocking
public class CategoriesResource implements CategoriesApi {
  private final CategoriesService categoryService;

  @Inject
  public CategoriesResource(CategoriesService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  public Response deleteCategory(Long categoryId) {
    return null;
  }

  @Override
  public Response getCategories() {
    return Response.ok(List.of(categoryService.get())).build();
  }

  @Override
  public Response getCategory(Long categoryId) {
    return null;
  }

  @Override
  public Response postCategory(ApiCategory category) {
    return null;
  }

  @Override
  public Response putCategory(Long categoryId, ApiCategory category) {
    return null;
  }
}
