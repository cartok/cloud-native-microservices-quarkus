package de.cartok.quarkus.tutorial.backoffice.article;

import de.cartok.quarkus.tutorial.backoffice.CrudService;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiArticle;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticlesService extends CrudService<ArticleEntity> {
  // This no-args constructor was necessary but is not used.
  public ArticlesService() {
    super(null);
  }

  public ApiArticle get() {
    return null;
  }

  @Override
  protected Class<ArticleEntity> getEntityClass() {
    return ArticleEntity.class;
  }
}
