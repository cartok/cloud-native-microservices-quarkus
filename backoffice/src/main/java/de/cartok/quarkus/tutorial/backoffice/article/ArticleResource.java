package de.cartok.quarkus.tutorial.backoffice.article;

import java.util.List;
import java.util.concurrent.CompletionStage;

import de.cartok.quarkus.tutorial.backoffice.api.ArticlesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiArticle;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("articles") // Temporarily added this path. Should come from schema or schema generated code should be abstract class not interface. It's only there for the manually added method `listByCategory`.
@ApplicationScoped
@RolesAllowed({"admin", "user"})
public class ArticleResource implements ArticlesApi {

  private final ArticleService service;

  @Inject
  public ArticleResource(ArticleService service) {
    this.service = service;
  }

  @Override
  public CompletionStage<Void> deleteArticle(Long id) {
    return service.deleteArticle(id).subscribeAsCompletionStage();
  }

  @Override
  public CompletionStage<ApiArticle> getArticle(Long id) {
    return service.getArticle(id).subscribeAsCompletionStage();
  }

  @Override
  public CompletionStage<List<ApiArticle>> getArticles() {
    return service.getArticles().subscribeAsCompletionStage();
  }

  @Override
  public CompletionStage<Void> postArticle(Long xCategoryId, ApiArticle apiArticle) {
    return service.postArticle(xCategoryId, apiArticle).subscribeAsCompletionStage();
  }

  @Override
  public CompletionStage<Void> putArticle(Long id, ApiArticle apiItem) {
    return service.putArticle(id, apiItem).subscribeAsCompletionStage();
  }

  @GET
  @Path("/category/{categoryId}")
  @Produces("application/json")
  public CompletionStage<List<ApiArticle>> listByCategory(@PathParam("categoryId") Long categoryId) {
    return service.listByCategory(categoryId).subscribeAsCompletionStage();
  }
}
