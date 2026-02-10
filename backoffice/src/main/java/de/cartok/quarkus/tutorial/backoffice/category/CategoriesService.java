package de.cartok.quarkus.tutorial.backoffice.category;

import de.cartok.quarkus.tutorial.backoffice.CrudService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class CategoriesService extends CrudService<CategoryEntity> {
  // This no-args constructor was necessary but is not used.
  public CategoriesService() {
    super(null);
  }

  @Inject
  public CategoriesService(final EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  protected Class<CategoryEntity> getEntityClass() {
    return CategoryEntity.class;
  }
}
