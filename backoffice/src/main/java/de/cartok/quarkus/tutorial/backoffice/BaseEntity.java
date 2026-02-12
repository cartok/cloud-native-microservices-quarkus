package de.cartok.quarkus.tutorial.backoffice;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
  @Id
  @GeneratedValue
  public Long id;
}
