package de.cartok.quarkus.tutorial.backoffice;

import de.cartok.quarkus.tutorial.backoffice.api.model.Category;
import io.quarkus.test.Mock;

@Mock
public class MockCategoriesService extends CategoriesService {
  @Override
  public Category get() {
    return new Category().name("mock");
  }
}
