package de.cartok.quarkus.tutorial.backoffice.service;

import jakarta.persistence.EntityManager;

public abstract class CrudService<Entity> {
  private final EntityManager entityManager;

  public CrudService(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Entity persist(Entity entity) {
    entityManager.persist(entity);
    return entity;
  }
}
