package de.cartok.quarkus.tutorial.backoffice.article;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.cartok.quarkus.tutorial.backoffice.api.ArticlesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiArticle;
import de.cartok.quarkus.tutorial.backoffice.category.CategoriesService;
import de.cartok.quarkus.tutorial.backoffice.category.CategoryEntity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class ArticlesResource implements ArticlesApi {

  private final ArticlesService articlesService;
  private final CategoriesService categoriesService;
  private final ArticleMapper mapper;

  @Inject
  public ArticlesResource(ArticlesService articlesService, CategoriesService categoriesService, ArticleMapper mapper) {
    this.articlesService = articlesService;
    this.categoriesService = categoriesService;
    this.mapper = mapper;
  }

  @Override
  public Response deleteArticle(Long articleId) {
    final Optional<ArticleEntity> optionalArticleEntity = articlesService.getById(articleId);
    if (optionalArticleEntity.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().build();
  }

  @Override
  public Response getArticle(Long articleId) {
    final Optional<ArticleEntity> optionalArticleEntity = articlesService.getById(articleId);
    if (optionalArticleEntity.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(mapper.mapToDto(optionalArticleEntity.get())).build();
  }

  @Override
  public Response getArticles() {
    final List<ArticleEntity> articleEntities = articlesService.listAll();
    return Response.ok(articleEntities.stream().map(mapper::mapToDto)).build();
  }

  @Override
  public Response postArticle(Long xCategoryId, ApiArticle apiArticle) {
    final Optional<CategoryEntity> category = categoriesService.getById(xCategoryId);
    if (category.isEmpty()) {
      return Response.status(
        Response.Status.NOT_FOUND.getStatusCode(),
        "Could not create article cause category with id=" + xCategoryId + " does not exist."
      ).build();
    }
    final ArticleEntity articleEntity = new ArticleEntity();
    mapper.mapToEntity(apiArticle, articleEntity);
    articleEntity.setCategory(category.get());
    final ArticleEntity persitedArticle = articlesService.persist(articleEntity);
    return Response.created(URI.create("/articles/" + persitedArticle.getId())).build();
  }

  @Override
  public Response putArticle(Long articleId, ApiArticle apiArticle) {
    final Optional<ArticleEntity> optionalArticleEntity = articlesService.getById(articleId);
    if (optionalArticleEntity.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    final ArticleEntity articleEntity = optionalArticleEntity.get();
    mapper.mapToEntity(apiArticle, articleEntity);
    articlesService.update(articleEntity);
    return Response.ok().build();
  }
}
