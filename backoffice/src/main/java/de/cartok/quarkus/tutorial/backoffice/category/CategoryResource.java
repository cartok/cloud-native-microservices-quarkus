package de.cartok.quarkus.tutorial.backoffice.category;

import java.util.List;
import java.util.concurrent.CompletionStage;

import de.cartok.quarkus.tutorial.backoffice.api.CategoriesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiCategory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CategoryResource implements CategoriesApi {

  private final CategoryService service;

  @Inject
  public CategoryResource(CategoryService service) {
    this.service = service;
  }

  @Override
  public CompletionStage<Void> deleteCategory(Long id) {
    return service.deleteCategory(id).subscribeAsCompletionStage();
  }

  @Override
  public CompletionStage<ApiCategory> getCategory(Long id) {
    return service.getCategory(id).subscribeAsCompletionStage();
  }

  @Override
  public CompletionStage<List<ApiCategory>> getCategories() {
    return service.getCategories().subscribeAsCompletionStage();
  }

  @Override
  public CompletionStage<Void> postCategory(ApiCategory apiItem) {
    return service.postCategory(apiItem).subscribeAsCompletionStage();
  }

  @Override
  public CompletionStage<Void> putCategory(Long id, ApiCategory apiItem) {
    return service.putCategory(id, apiItem).subscribeAsCompletionStage();
  }
}
