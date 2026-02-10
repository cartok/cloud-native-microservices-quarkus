package de.cartok.quarkus.tutorial.backoffice.article;

import de.cartok.quarkus.tutorial.backoffice.CrudService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ArticlesService extends CrudService<ArticleEntity> {
  // This no-args constructor was necessary but is not used.
  public ArticlesService() {
    super(null);
  }

  @Inject
  public ArticlesService(final EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  protected Class<ArticleEntity> getEntityClass() {
    return ArticleEntity.class;
  }
}
