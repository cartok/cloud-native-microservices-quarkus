package de.cartok.quarkus.tutorial.backoffice.category;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.cartok.quarkus.tutorial.backoffice.api.CategoriesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiCategory;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class CategoriesResource implements CategoriesApi {
  private final CategoriesService categoriesService;
  private final CategoryMapper mapper;

  @Inject
  public CategoriesResource(CategoriesService categoriesService, CategoryMapper mapper) {
    this.categoriesService = categoriesService;
    this.mapper = mapper;
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
    return Response.ok(categories.stream().map(mapper::mapToDto).toList())
      .build();
  }

  @Override
  public Response getCategory(Long categoryId) {
    final Optional<CategoryEntity> category = categoriesService.getById(categoryId);
    if (category.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(mapper.mapToDto(category.get())).build();
  }

  @Override
  public Response postCategory(ApiCategory apiCategory) {
    final CategoryEntity categoryEntity = new CategoryEntity();
    mapper.mapToEntity(apiCategory, categoryEntity);
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
    mapper.mapToEntity(apiCategory, categoryEntity);
    categoriesService.update(categoryEntity);
    return Response.ok().build();
  }
}
