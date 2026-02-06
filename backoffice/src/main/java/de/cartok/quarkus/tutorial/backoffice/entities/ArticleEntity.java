package de.cartok.quarkus.tutorial.backoffice.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity
public class ArticleEntity extends BaseEntity {
  private String name;
  private BigDecimal price;
  private String description;
  private String picture;

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

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }
}
