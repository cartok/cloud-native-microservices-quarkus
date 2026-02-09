package de.cartok.quarkus.tutorial.backoffice.table;

import java.net.URI;

import de.cartok.quarkus.tutorial.backoffice.api.TablesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiTable;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@NonBlocking
public class TablesResource implements TablesApi {
  private final TablesService tablesService;

  @Inject
  public TablesResource(TablesService tablesService) {
    this.tablesService = tablesService;
  }

  @Override
  public Response deleteTable(Long tableId) {
    return null;
  }

  @Override
  public Response getTable(Long tableId) {
    return null;
  }

  @Override
  public Response getTables() {
    final var tables = tablesService.getAll();
    return null;
  }

  @Override
  public Response postTable(ApiTable table) {
    final var tableEntity = new TableEntity();
    tableEntity.setActive(table.getActive());
    tableEntity.setName(table.getName());
    tableEntity.setSeatCount(table.getSeatCount());
    final var persistedTable = tablesService.persist(tableEntity);
    final var response = Response.created(URI.create("/tables/" + persistedTable.getId())).build();
    return response;
  }

  @Override
  public Response putTable(Long tableId, ApiTable table) {
    return null;
  }
}
