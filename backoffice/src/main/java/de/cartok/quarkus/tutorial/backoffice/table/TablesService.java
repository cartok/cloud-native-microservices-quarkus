package de.cartok.quarkus.tutorial.backoffice.table;

import de.cartok.quarkus.tutorial.backoffice.CrudService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class TablesService extends CrudService<TableEntity> {
  // This no-args constructor was necessary but is not used.
  public TablesService() {
    super(null);
  }

  @Inject
  public TablesService(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  protected Class<TableEntity> getEntityClass() {
    return TableEntity.class;
  }
}
