package de.cartok.quarkus.tutorial.backoffice.category;

import de.cartok.quarkus.tutorial.backoffice.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "category", schema = "smartbar", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"name"})
})
public class Category extends BaseEntity {
  // TODO: Felder sollten für put operationen aber optional sein!
  @NotNull
  public String name;

  @NotNull
  public String description;
}
