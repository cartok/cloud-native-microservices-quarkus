package de.cartok.quarkus.tutorial.backoffice.category;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.cartok.quarkus.tutorial.backoffice.api.CategoriesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiCategory;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

public class CategoriesResource implements CategoriesApi {
  private final CategoryMapper mapper;
  private final CategoryRepository categoryRepository;

  @Inject
  public CategoriesResource(CategoryMapper mapper, CategoryRepository categoryRepository) {
    this.mapper = mapper;
    this.categoryRepository = categoryRepository;
  }

  @Override
  @Transactional
  public Response deleteCategory(Long categoryId) {
    final boolean success = categoryRepository.deleteById(categoryId);
    if (!success) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().build();
  }

  @Override
  @Transactional
  public Response getCategories() {
    final List<CategoryEntity> categories = categoryRepository.listAll();
    return Response.ok(categories.stream().map(mapper::mapToDto).toList())
      .build();
  }

  @Override
  @Transactional
  public Response getCategory(Long categoryId) {
    final Optional<CategoryEntity> category = categoryRepository.findByIdOptional(categoryId);
    if (category.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(mapper.mapToDto(category.get())).build();
  }

  @Override
  @Transactional
  public Response postCategory(ApiCategory apiCategory) {
    final CategoryEntity categoryEntity = new CategoryEntity();
    mapper.mapToEntity(apiCategory, categoryEntity);
    categoryRepository.persist(categoryEntity);
    return Response.created(URI.create("/categories/" + categoryEntity.id)).build();
  }

  @Override
  @Transactional
  public Response putCategory(Long categoryId, ApiCategory apiCategory) {
    final Optional<CategoryEntity> existingCategory = categoryRepository.findByIdOptional(categoryId);
    if (existingCategory.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    final CategoryEntity categoryEntity = existingCategory.get();
    mapper.mapToEntity(apiCategory, categoryEntity);
    return Response.ok().build();
  }
}
