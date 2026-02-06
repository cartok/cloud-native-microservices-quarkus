package de.cartok.quarkus.tutorial.backoffice.api;

import java.util.List;

import de.cartok.quarkus.tutorial.backoffice.api.model.Category;
import de.cartok.quarkus.tutorial.backoffice.service.CategoriesService;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@NonBlocking
public class CategoriesApiImpl implements CategoriesApi {
  private final CategoriesService categoryService;

  @Inject
  public CategoriesApiImpl(CategoriesService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  public Response deleteCategory(String categoryId) {
    return null;
  }

  @Override
  public Response getCategories() {
    return Response.ok(List.of(categoryService.get())).build();
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
