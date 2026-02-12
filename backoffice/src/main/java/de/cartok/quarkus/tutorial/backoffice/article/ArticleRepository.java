package de.cartok.quarkus.tutorial.backoffice.article;

import java.util.List;

import de.cartok.quarkus.tutorial.backoffice.category.CategoryEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticleRepository implements PanacheRepository<ArticleEntity> {
  public List<ArticleEntity> listByCategory(CategoryEntity categoryEntity) {
    return this.list("category", categoryEntity);
  }
}
