package de.cartok.quarkus.tutorial.backoffice.article;

import java.util.List;

import de.cartok.quarkus.tutorial.backoffice.api.model.ApiArticle;
import de.cartok.quarkus.tutorial.backoffice.category.Category;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.ws.rs.*;

@ApplicationScoped
@NamedQueries({
  @NamedQuery(name = "Article.byCategory", query = "from Article where category = ?1 order by price desc")
})
public class ArticleService {

  private final ArticleMapper mapper;

  @Inject
  public ArticleService(ArticleMapper mapper) {
    this.mapper = mapper;
  }

  @WithTransaction
  public Uni<Void> deleteArticle(Long id) {
    return Article.deleteById(id)
      .onItem().transform(success -> {
        if (!success) {
          throw new NotFoundException();
        }
        return true;
      })
      .replaceWithVoid()
      ;
  }

  @WithSession
  public Uni<ApiArticle> getArticle(Long id) {
    return Article.<Article>findById(id)
      .onItem().ifNull().failWith(NotFoundException::new)
      .map(mapper::mapToDto)
      ;
  }

  @WithSession
  public Uni<List<ApiArticle>> getArticles() {
    return Article.<Article>listAll()
      .map(items -> items.stream().map(mapper::mapToDto).toList())
      ;
  }

  @WithTransaction
  public Uni<Void> postArticle(Long xCategoryId, ApiArticle apiArticle) {
    return Category.<Category>findById(xCategoryId)
      .onItem().ifNull().failWith(() -> new NotFoundException("Could not create article cause category with id=" + xCategoryId + " does not exist."))
      .flatMap(category -> {
        final Article article = new Article();
        mapper.mapToEntity(apiArticle, article);
        article.category = category;
        return article.persist();
      })
      .replaceWithVoid()
      ;
  }

  @WithTransaction
  public Uni<Void> putArticle(Long id, ApiArticle apiItem) {
    return Article.<Article>findById(id)
      .onItem().ifNull().failWith(NotFoundException::new)
      .flatMap(oldItem -> {
        mapper.mapToEntity(apiItem, oldItem);
        return oldItem.persistAndFlush();
      })
      .replaceWithVoid()
      ;
  }

  @GET
  @Path("/category/{categoryId}")
  @Produces("application/json")
  @WithSession
  public Uni<List<ApiArticle>> listByCategory(@PathParam("categoryId") Long categoryId) {
    return Category.<Category>findById(categoryId)
      .onItem().ifNull().failWith(() ->
        new NotFoundException(
          "Could get articles for category with id=" +
            categoryId + " cause category does not exist."
        )
      )
      .flatMap(Article::listByCategory)
      .map(articles ->
        articles.stream()
          .map(mapper::mapToDto)
          .toList()
      )
      ;
  }
}
