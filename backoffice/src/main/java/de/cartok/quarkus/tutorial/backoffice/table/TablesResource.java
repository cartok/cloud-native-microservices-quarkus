package de.cartok.quarkus.tutorial.backoffice.table;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
  public Response getTable(Long tableId) {
    final List<TableEntity> tableEntities = tablesService.listAll();
    return Response.ok(tableEntities.stream().map(this::mapTableToApiTable).toList())
      .build();
  }

  @Override
  public Response getTables() {
    final List<TableEntity> tableEntities = tablesService.listAll();
    return Response.ok(tableEntities.stream().map(this::mapTableToApiTable).toList())
      .build();
  }

  @Override
  public Response postTable(ApiTable apiTable) {
    final TableEntity tableEntity = new TableEntity();
    mapApiTableToTable(apiTable, tableEntity);
    final TableEntity persitedTable = tablesService.persist(tableEntity);
    return Response.created(URI.create("/tables/" + persitedTable.getId())).build();
  }

  @Override
  public Response putTable(Long tableId, ApiTable apiTable) {
    final Optional<TableEntity> existingTable = tablesService.getById(tableId);
    if (existingTable.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    final TableEntity tableEntity = existingTable.get();
    mapApiTableToTable(apiTable, tableEntity);
    tablesService.update(tableEntity);
    return Response.ok().build();
  }
  
  @Override
  public Response deleteTable(Long tableId) {
    final Optional<TableEntity> tableEntities = tablesService.deleteById(tableId);
    if (tableEntities.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().build();
  }

  private void mapApiTableToTable(ApiTable apiTable, TableEntity tableEntity) {
    tableEntity.setName(apiTable.getName());
    tableEntity.setSeatCount(apiTable.getSeatCount());
    tableEntity.setActive(apiTable.getActive());
  }

  private ApiTable mapTableToApiTable(TableEntity tableEntity) {
    final ApiTable apiTable = new ApiTable();
    apiTable.setActive(tableEntity.getActive());
    apiTable.setName(tableEntity.getName());
    apiTable.setSeatCount(tableEntity.getSeatCount());
    apiTable.setId(tableEntity.getId());
    return apiTable;
  }
}
