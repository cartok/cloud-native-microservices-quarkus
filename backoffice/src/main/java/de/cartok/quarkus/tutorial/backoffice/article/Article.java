package de.cartok.quarkus.tutorial.backoffice.article;

import java.math.BigDecimal;
import java.util.List;

import de.cartok.quarkus.tutorial.backoffice.category.Category;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity()
@Table(name = "article", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"name", "category_id"})
})
public class Article extends PanacheEntity {
  @NotNull
  public String name;

  @NotNull
  @Positive
  public BigDecimal price;

  @NotNull
  public String description;

  @NotNull
  public String pictureBase64;

  @ManyToOne(optional = false)
  @JoinColumn(name = "category_id", nullable = false)
  public Category category;

  public static List<Article> listByCategory(Category category) {
    //    return find("category", Sort.by("price", Sort.Direction.Descending), categoryEntity)
    //      .page(Page.ofSize(3))
    //      .list()
    //      ;
    // Didnt work. "The named query 'ArticleEntity.byCategory' must be defined on your JPA entity or one of its super classes"
    return find("#ArticleEntity.byCategory", category).list();
  }
}
