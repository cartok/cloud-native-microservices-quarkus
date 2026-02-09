package de.cartok.quarkus.tutorial.backoffice.category;

import de.cartok.quarkus.tutorial.backoffice.CrudService;
import de.cartok.quarkus.tutorial.backoffice.api.model.Category;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoriesService extends CrudService<CategoryEntity> {
  // This no-args constructor was necessary but is not used.
  public CategoriesService() {
    super(null);
  }

  public Category get() {
    return new Category().name("fisch");
  }
}
