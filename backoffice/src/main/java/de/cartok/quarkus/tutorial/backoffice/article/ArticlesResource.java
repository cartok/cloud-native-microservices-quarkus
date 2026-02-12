package de.cartok.quarkus.tutorial.backoffice.article;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.cartok.quarkus.tutorial.backoffice.api.ArticlesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiArticle;
import de.cartok.quarkus.tutorial.backoffice.category.CategoryEntity;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

public class ArticlesResource implements ArticlesApi {

  private final ArticleMapper mapper;

  @Inject
  public ArticlesResource(ArticleMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  @Transactional
  public Response deleteArticle(Long articleId) {
    final boolean success = ArticleEntity.deleteById(articleId);
    if (!success) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().build();
  }

  @Override
  @Transactional
  public Response getArticle(Long articleId) {
    final Optional<ArticleEntity> optionalArticleEntity = ArticleEntity.findByIdOptional(articleId);
    if (optionalArticleEntity.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(mapper.mapToDto(optionalArticleEntity.get())).build();
  }

  @Override
  @Transactional
  public Response getArticles() {
    final List<ArticleEntity> articleEntities = ArticleEntity.listAll();
    return Response.ok(articleEntities.stream().map(mapper::mapToDto)).build();
  }

  @Override
  @Transactional
  public Response postArticle(Long xCategoryId, ApiArticle apiArticle) {
    final Optional<CategoryEntity> category = CategoryEntity.findByIdOptional(xCategoryId);
    if (category.isEmpty()) {
      return Response.status(
        Response.Status.NOT_FOUND.getStatusCode(),
        "Could not create article cause category with id=" + xCategoryId + " does not exist."
      ).build();
    }
    final ArticleEntity articleEntity = new ArticleEntity();
    mapper.mapToEntity(apiArticle, articleEntity);
    articleEntity.category = category.get();
    articleEntity.persist();
    return Response.created(URI.create("/articles/" + articleEntity.id)).build();
  }

  @Override
  @Transactional
  public Response putArticle(Long articleId, ApiArticle apiArticle) {
    final Optional<ArticleEntity> optionalArticleEntity = ArticleEntity.findByIdOptional(articleId);
    if (optionalArticleEntity.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    final ArticleEntity articleEntity = optionalArticleEntity.get();
    mapper.mapToEntity(apiArticle, articleEntity);
    return Response.ok().build();
  }
}
