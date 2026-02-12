package de.cartok.quarkus.tutorial.backoffice.article;

import java.math.BigDecimal;

import de.cartok.quarkus.tutorial.backoffice.BaseEntity;
import de.cartok.quarkus.tutorial.backoffice.category.CategoryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity()
@Table(name = "article", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"name", "category_id"})
})
public class ArticleEntity extends BaseEntity {
  @NotNull
  public String name;

  @NotNull
  @Positive
  public BigDecimal price;

  @NotNull
  public String description;

  @NotNull
  public String pictureBase64;

  @ManyToOne
  public CategoryEntity category;
}
