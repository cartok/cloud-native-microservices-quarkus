package de.cartok.quarkus.tutorial.backoffice.category;

import de.cartok.quarkus.tutorial.backoffice.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "category", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"name"})
})
public class CategoryEntity extends BaseEntity {
  @NotNull
  private String name;
  
  @NotNull
  private String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
