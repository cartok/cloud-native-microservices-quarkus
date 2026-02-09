package de.cartok.quarkus.tutorial.backoffice.article;

import java.math.BigDecimal;

import de.cartok.quarkus.tutorial.backoffice.BaseEntity;
import jakarta.persistence.Entity;

@Entity
public class ArticleEntity extends BaseEntity {
  private String name;
  private BigDecimal price;
  private String description;
  private String pictureBase64;

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
}
