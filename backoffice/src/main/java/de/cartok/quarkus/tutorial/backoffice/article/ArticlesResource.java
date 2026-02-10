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

  @Inject
  public ArticlesResource(final ArticlesService articlesService, final CategoriesService categoriesService) {
    this.articlesService = articlesService;
    this.categoriesService = categoriesService;
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
    return Response.ok().build();
  }

  @Override
  public Response getArticles() {
    final List<ArticleEntity> articleEntities = articlesService.listAll();
    return Response.ok(articleEntities.stream().map(this::mapArticleEntityToApiArticle)).build();
  }

  @Override
  public Response postArticle(Long xCategoryId, ApiArticle apiArticle) {
    final Optional<CategoryEntity> category = categoriesService.getById(xCategoryId);
    if (category.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    final ArticleEntity articleEntity = new ArticleEntity();
    mergeApiArticleIntoArticleEntity(apiArticle, articleEntity);
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
    mergeApiArticleIntoArticleEntity(apiArticle, articleEntity);
    articlesService.update(articleEntity);
    return Response.ok().build();
  }

  public void mergeApiArticleIntoArticleEntity(ApiArticle apiArticle, ArticleEntity articleEntity) {
    articleEntity.setDescription(apiArticle.getDescription());
    articleEntity.setName(apiArticle.getName());
    articleEntity.setPrice(apiArticle.getPrice());
    articleEntity.setPictureBase64(apiArticle.getPicture());
  }

  public ApiArticle mapArticleEntityToApiArticle(ArticleEntity articleEntity) {
    final ApiArticle apiArticle = new ApiArticle();
    apiArticle.setDescription(articleEntity.getDescription());
    apiArticle.setName(articleEntity.getName());
    apiArticle.setPrice(articleEntity.getPrice());
    apiArticle.setPicture(articleEntity.getPictureBase64());
    apiArticle.setId(articleEntity.getId());
    return apiArticle;
  }
}
