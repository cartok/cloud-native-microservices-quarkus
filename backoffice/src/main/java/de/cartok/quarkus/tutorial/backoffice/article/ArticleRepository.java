package de.cartok.quarkus.tutorial.backoffice.article;

import java.util.List;

import de.cartok.quarkus.tutorial.backoffice.category.Category;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticleRepository implements PanacheRepository<Article> {
  public List<Article> listByCategory(Category category) {
    //    return find("category", Sort.by("price", Sort.Direction.Descending), categoryEntity)
    //      .page(Page.ofSize(3))
    //      .list()
    //      ;
    // Didnt work. "The named query 'ArticleEntity.byCategory' must be defined on your JPA entity or one of its super classes"
    return find("#ArticleEntity.byCategory", category).list();
  }
}
