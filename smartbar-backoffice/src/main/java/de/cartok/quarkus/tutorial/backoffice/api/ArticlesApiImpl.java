package de.cartok.quarkus.tutorial.backoffice.api;

import de.cartok.quarkus.tutorial.backoffice.api.model.Article;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.ws.rs.core.Response;

@NonBlocking
public class ArticlesApiImpl implements ArticlesApi {
  @Override
  public Response deleteArticle(String articleId) {
    return null;
  }

  @Override
  public Response getArticle(String articleId) {
    return null;
  }

  @Override
  public Response getArticles() {
    return null;
  }

  @Override
  public Response postArticle(Article article) {
    return null;
  }

  @Override
  public Response putArticle(String articleId, Article article) {
    return null;
  }
}
