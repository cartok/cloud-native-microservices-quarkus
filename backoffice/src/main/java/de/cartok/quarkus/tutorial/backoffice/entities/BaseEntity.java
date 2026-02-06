package de.cartok.quarkus.tutorial.backoffice.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
  @Id
  @GeneratedValue
  private Long id;

  public Long getId() {
    return id;
  }

  private void setId(Long id) {
    this.id = id;
  }
}
