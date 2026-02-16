package de.cartok.quarkus.tutorial.backoffice.article;

import java.math.BigDecimal;

import de.cartok.quarkus.tutorial.backoffice.BaseEntity;
import de.cartok.quarkus.tutorial.backoffice.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity()
@Table(name = "article", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"name", "category_id"})
})
public class Article extends BaseEntity {
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
}
