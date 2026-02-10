package de.cartok.quarkus.tutorial.backoffice;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

public abstract class CrudService<Entity extends BaseEntity> {
  private final EntityManager entityManager;

  public CrudService(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Transactional
  public Entity persist(Entity entity) {
    entityManager.persist(entity);
    return entity;
  }

  @Transactional
  public Entity update(Entity detachedEntity) {
    return entityManager.merge(detachedEntity);
  }

  @Transactional
  public List<Entity> listAll() {
    final CriteriaQuery<Entity> query = entityManager.getCriteriaBuilder().createQuery(getEntityClass());
    final Root<Entity> root = query.from(getEntityClass());
    query.select(root);
    return entityManager.createQuery(query).getResultList();
  }

  @Transactional
  public Optional<Entity> getById(Long id) {
    return Optional.ofNullable(entityManager.find(getEntityClass(), id));
  }

  @Transactional
  public Optional<Entity> deleteById(Long id) {
    final Optional<Entity> entity = getById(id);
    entity.ifPresent(entityManager::remove);
    return entity;
  }

  protected abstract Class<Entity> getEntityClass();
}
