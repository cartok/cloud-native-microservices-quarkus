package de.cartok.quarkus.tutorial.backoffice.table;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.cartok.quarkus.tutorial.backoffice.api.TablesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiTable;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class TablesResource implements TablesApi {
  private final TablesService tablesService;
  private final TableMapper mapper;

  @Inject
  public TablesResource(TablesService tablesService, TableMapper mapper) {
    this.tablesService = tablesService;
    this.mapper = mapper;
  }

  @Override
  public Response deleteTable(Long tableId) {
    final Optional<TableEntity> tableEntities = tablesService.deleteById(tableId);
    if (tableEntities.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().build();
  }

  @Override
  public Response getTable(Long tableId) {
    final Optional<TableEntity> tableEntity = tablesService.getById(tableId);
    if (tableEntity.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    } else {
      return Response.ok(mapper.mapToDto(tableEntity.get())).build();
    }
  }

  @Override
  public Response getTables() {
    final List<TableEntity> tableEntities = tablesService.listAll();
    return Response.ok(tableEntities.stream().map(mapper::mapToDto).toList())
      .build();
  }

  @Override
  public Response postTable(ApiTable apiTable) {
    final TableEntity tableEntity = new TableEntity();
    mapper.mapToEntity(apiTable, tableEntity);
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
    mapper.mapToEntity(apiTable, tableEntity);
    tablesService.update(tableEntity);
    return Response.ok().build();
  }
}
