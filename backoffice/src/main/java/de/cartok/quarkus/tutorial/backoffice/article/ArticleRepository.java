package de.cartok.quarkus.tutorial.backoffice.article;

import java.util.List;

import de.cartok.quarkus.tutorial.backoffice.category.CategoryEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticleRepository implements PanacheRepository<ArticleEntity> {
  public List<ArticleEntity> listByCategory(CategoryEntity categoryEntity) {
    return find("category", Sort.by("price", Sort.Direction.Descending), categoryEntity)
      .page(Page.ofSize(3))
      .list()
      ;
  }
}
