package de.cartok.quarkus.tutorial.backoffice.api;

import de.cartok.quarkus.tutorial.backoffice.TablesService;
import de.cartok.quarkus.tutorial.backoffice.api.model.Table;
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
    return null;
  }

  @Override
  public Response putTable(String tableId, Table table) {
    return null;
  }
}
