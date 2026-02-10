package de.cartok.quarkus.tutorial.backoffice.category;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.cartok.quarkus.tutorial.backoffice.api.CategoriesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiCategory;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@NonBlocking
public class CategoriesResource implements CategoriesApi {
  private final CategoriesService categoriesService;

  @Inject
  public CategoriesResource(CategoriesService categoriesService) {
    this.categoriesService = categoriesService;
  }

  @Override
  public Response deleteCategory(Long categoryId) {
    final Optional<CategoryEntity> category = categoriesService.deleteById(categoryId);
    if (category.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().build();
  }

  @Override
  public Response getCategories() {
    final List<CategoryEntity> categories = categoriesService.listAll();
    return Response.ok(categories.stream().map(this::mapCategoryToApiCategory).toList())
      .build();
  }

  @Override
  public Response getCategory(Long categoryId) {
    final Optional<CategoryEntity> category = categoriesService.getById(categoryId);
    if (category.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(mapCategoryToApiCategory(category.get())).build();
  }

  @Override
  public Response postCategory(ApiCategory apiCategory) {
    final CategoryEntity categoryEntity = new CategoryEntity();
    mapApiCategoryToCategory(apiCategory, categoryEntity);
    final CategoryEntity persitedCategory = categoriesService.persist(categoryEntity);
    return Response.created(URI.create("/categories/" + persitedCategory.getId())).build();
  }

  @Override
  public Response putCategory(Long categoryId, ApiCategory apiCategory) {
    final Optional<CategoryEntity> existingCategory = categoriesService.getById(categoryId);
    if (existingCategory.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    final CategoryEntity categoryEntity = existingCategory.get();
    mapApiCategoryToCategory(apiCategory, categoryEntity);
    categoriesService.update(categoryEntity);
    return Response.ok().build();
  }

  private void mapApiCategoryToCategory(ApiCategory apiCategory, CategoryEntity category) {
    category.setName(apiCategory.getName());
    category.setDescription(apiCategory.getDescription());
  }

  private ApiCategory mapCategoryToApiCategory(CategoryEntity category) {
    final ApiCategory apiCategory = new ApiCategory();
    apiCategory.setDescription(category.getDescription());
    apiCategory.setName(category.getName());
    apiCategory.setId(category.getId());
    return apiCategory;
  }
}
