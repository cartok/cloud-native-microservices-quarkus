package de.cartok.quarkus.tutorial.backoffice.article;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.cartok.quarkus.tutorial.backoffice.api.ArticlesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiArticle;
import de.cartok.quarkus.tutorial.backoffice.category.CategoryEntity;
import de.cartok.quarkus.tutorial.backoffice.category.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("articles") // Temporarily added this path. Should come from schema or schema generated code should be abstract class not interface.
@ApplicationScoped
@NamedQueries({
  @NamedQuery(name = "Article.byCategory", query = "from ArticleEntity where category = ?1 order by price desc")
})
public class ArticleResource implements ArticlesApi {

  private final ArticleMapper mapper;
  private final ArticleRepository articleRepository;
  private final CategoryRepository categoryRepository;

  @Inject
  public ArticleResource(ArticleMapper mapper, ArticleRepository articleRepository, CategoryRepository categoryRepository) {
    this.mapper = mapper;
    this.articleRepository = articleRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  @Transactional
  public Response deleteArticle(Long articleId) {
    final boolean success = articleRepository.deleteById(articleId);
    if (!success) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().build();
  }

  @Override
  @Transactional
  public Response getArticle(Long articleId) {
    final Optional<ArticleEntity> optionalArticleEntity = articleRepository.findByIdOptional(articleId);
    if (optionalArticleEntity.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(mapper.mapToDto(optionalArticleEntity.get())).build();
  }

  @Override
  @Transactional
  public Response getArticles() {
    final List<ArticleEntity> articleEntities = articleRepository.listAll();
    return Response.ok(articleEntities.stream().map(mapper::mapToDto)).build();
  }

  @Override
  @Transactional
  public Response postArticle(Long xCategoryId, ApiArticle apiArticle) {
    final Optional<CategoryEntity> categoryEntity = categoryRepository.findByIdOptional(xCategoryId);
    if (categoryEntity.isEmpty()) {
      return Response.status(
        Response.Status.NOT_FOUND.getStatusCode(),
        "Could not create article cause category with id=" + xCategoryId + " does not exist."
      ).build();
    }
    final ArticleEntity articleEntity = new ArticleEntity();
    mapper.mapToEntity(apiArticle, articleEntity);
    articleEntity.category = categoryEntity.get();
    articleRepository.persist(articleEntity);
    return Response.created(URI.create("/articles/" + articleEntity.id)).build();
  }

  @Override
  @Transactional
  public Response putArticle(Long articleId, ApiArticle apiArticle) {
    final Optional<ArticleEntity> optionalArticleEntity = articleRepository.findByIdOptional(articleId);
    if (optionalArticleEntity.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    final ArticleEntity articleEntity = optionalArticleEntity.get();
    mapper.mapToEntity(apiArticle, articleEntity);
    return Response.ok().build();
  }

  @GET
  @Path("/category/{categoryId}")
  @Produces("application/json")
  public Response listByCategory(@PathParam("categoryId") Long categoryId) {
    final Optional<CategoryEntity> categoryEntity = categoryRepository.findByIdOptional(categoryId);
    if (categoryEntity.isEmpty()) {
      return Response.status(
        Response.Status.NOT_FOUND.getStatusCode(),
        "Could get articles for category with id=" + categoryId + " cause category does not exist."
      ).build();
    }
    final List<ApiArticle> articles = articleRepository
      .listByCategory(categoryEntity.get())
      .stream()
      .map(mapper::mapToDto)
      .toList()
      ;
    return Response.ok(articles).build();
  }
}
