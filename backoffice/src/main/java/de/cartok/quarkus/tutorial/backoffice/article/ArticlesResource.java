package de.cartok.quarkus.tutorial.backoffice.article;

import de.cartok.quarkus.tutorial.backoffice.api.ArticlesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiArticle;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@NonBlocking
public class ArticlesResource implements ArticlesApi {

  private final ArticlesService articlesService;

  @Inject
  public ArticlesResource(final ArticlesService articlesService) {
    this.articlesService = articlesService;
  }

  @Override
  public Response deleteArticle(Long articleId) {
    return null;
  }

  @Override
  public Response getArticle(Long articleId) {
    return null;
  }

  @Override
  public Response getArticles() {
    return null;
  }

  @Override
  public Response postArticle(Long xCategoryId, ApiArticle apiArticle) {
    return null;
  }

  @Override
  public Response putArticle(Long articleId, ApiArticle apiArticle) {
    return null;
  }
}
