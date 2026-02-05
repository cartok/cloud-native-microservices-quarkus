package de.cartok.quarkus.tutorial.backoffice.api;

import de.cartok.quarkus.tutorial.backoffice.api.model.Table;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.ws.rs.core.Response;

@NonBlocking
public class TablesApiImpl implements TablesApi {
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
