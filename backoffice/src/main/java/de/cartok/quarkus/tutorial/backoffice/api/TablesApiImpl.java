package de.cartok.quarkus.tutorial.backoffice.api;

import java.net.URI;

import de.cartok.quarkus.tutorial.backoffice.api.model.Table;
import de.cartok.quarkus.tutorial.backoffice.entities.TableEntity;
import de.cartok.quarkus.tutorial.backoffice.service.TablesService;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@NonBlocking
public class TablesApiImpl implements TablesApi {
  private final TablesService tablesService;

  @Inject
  public TablesApiImpl(TablesService tablesService) {
    this.tablesService = tablesService;
  }

  @Override
  public Response deleteTable(String tableId) {
    return null;
  }

  @Override
  public Response getTable(String tableId) {
    return null;
  }

  @Override
  public Response getTables() {
    return null;
  }

  @Override
  public Response postTable(Table table) {
    // Warum hat der tutor im api layer db object erstellt und nicht im service?
    final var tableEntity = new TableEntity();
    tableEntity.setActive(table.getActive());
    tableEntity.setName(table.getName());
    tableEntity.setSeatCount(table.getSeatCount());
    // Warum nimmt der table service nicht ein model an und erzeugt die entity?
    final var persistedTable = tablesService.persist(tableEntity);
    // TODO: Error handling fehlt
    final var response = Response.created(URI.create("/tables/" + persistedTable.getId())).build();
    return response;
  }

  @Override
  public Response putTable(String tableId, Table table) {
    return null;
  }
}
