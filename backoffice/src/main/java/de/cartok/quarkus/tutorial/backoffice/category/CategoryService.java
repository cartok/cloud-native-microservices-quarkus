package de.cartok.quarkus.tutorial.backoffice.category;

import java.util.List;

import de.cartok.quarkus.tutorial.backoffice.api.model.ApiCategory;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CategoryService {
  private final CategoryMapper mapper;

  @Inject
  public CategoryService(CategoryMapper mapper) {
    this.mapper = mapper;
  }

  @WithTransaction
  public Uni<Void> deleteCategory(Long id) {
    return Category.deleteById(id)
      .onItem().transform(success -> {
        if (!success) {
          throw new NotFoundException();
        }
        return true;
      })
      .replaceWithVoid()
      ;
  }

  @WithSession
  public Uni<ApiCategory> getCategory(Long id) {
    return Category.<Category>findById(id)
      .onItem().ifNull().failWith(NotFoundException::new)
      .map(mapper::mapToDto)
      ;
  }

  @WithSession
  public Uni<List<ApiCategory>> getCategories() {
    return Category.<Category>listAll()
      .map(items -> items.stream().map(mapper::mapToDto).toList())
      ;
  }

  @WithTransaction
  public Uni<Void> postCategory(ApiCategory apiCategory) {
    final Category category = new Category();
    mapper.mapToEntity(apiCategory, category);

    return category.persist()
      .replaceWithVoid()
      ;
  }

  @WithTransaction
  public Uni<Void> putCategory(Long id, ApiCategory apiItem) {
    return Category.<Category>findById(id)
      .onItem().ifNull().failWith(NotFoundException::new)
      .flatMap(oldItem -> {
        mapper.mapToEntity(apiItem, oldItem);
        return oldItem.persistAndFlush();
      })
      .replaceWithVoid()
      ;
  }
}
