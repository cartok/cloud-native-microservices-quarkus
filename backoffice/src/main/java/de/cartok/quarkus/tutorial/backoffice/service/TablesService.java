package de.cartok.quarkus.tutorial.backoffice.service;

import java.util.List;

import de.cartok.quarkus.tutorial.backoffice.api.model.Table;
import de.cartok.quarkus.tutorial.backoffice.entities.TableEntity;
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

  public List<Table> getAll() {
    return List.of(new Table().name("kröte"));
  }

  public Table get() {
    return new Table().name("fisch");
  }
}
