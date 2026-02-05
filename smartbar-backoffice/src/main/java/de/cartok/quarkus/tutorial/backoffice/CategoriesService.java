package de.cartok.quarkus.tutorial.backoffice;

import de.cartok.quarkus.tutorial.backoffice.api.model.Category;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoriesService {
  public Category get() {
    return new Category().name("fisch");
  }
}
