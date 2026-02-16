package de.cartok.quarkus.tutorial.backoffice.category;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.cartok.quarkus.tutorial.backoffice.api.CategoriesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiCategory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class CategoryResource implements CategoriesApi {
  private final CategoryMapper mapper;

  @Inject
  public CategoryResource(CategoryMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  @Transactional
  public Response deleteCategory(Long categoryId) {
    final boolean success = Category.deleteById(categoryId);
    if (!success) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().build();
  }

  @Override
  @Transactional
  public Response getCategories() {
    final List<Category> categories = Category.listAll();
    return Response.ok(categories.stream().map(mapper::mapToDto).toList())
      .build();
  }

  @Override
  @Transactional
  public Response getCategory(Long categoryId) {
    final Optional<Category> category = Category.findByIdOptional(categoryId);
    if (category.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(mapper.mapToDto(category.get())).build();
  }

  @Override
  @Transactional
  public Response postCategory(ApiCategory apiCategory) {
    final Category category = new Category();
    mapper.mapToEntity(apiCategory, category);
    category.persist();
    return Response.created(URI.create("/categories/" + category.id)).build();
  }

  @Override
  @Transactional
  public Response putCategory(Long categoryId, ApiCategory apiCategory) {
    final Optional<Category> existingCategory = Category.findByIdOptional(categoryId);
    if (existingCategory.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    final Category category = existingCategory.get();
    mapper.mapToEntity(apiCategory, category);
    return Response.ok().build();
  }
}
