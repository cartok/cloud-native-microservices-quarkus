package de.cartok.quarkus.tutorial.backoffice.article;

import java.math.BigDecimal;

import de.cartok.quarkus.tutorial.backoffice.BaseEntity;
import de.cartok.quarkus.tutorial.backoffice.category.CategoryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity()
@Table(name = "article")
public class ArticleEntity extends BaseEntity {
  private String name;
  private BigDecimal price;
  private String description;
  private String pictureBase64;

  @ManyToOne
  private CategoryEntity category;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPictureBase64() {
    return pictureBase64;
  }

  public void setPictureBase64(String picture) {
    this.pictureBase64 = picture;
  }

  public CategoryEntity getCategory() {
    return category;
  }

  public void setCategory(CategoryEntity category) {
    this.category = category;
  }
}
