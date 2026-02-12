package de.cartok.quarkus.tutorial.backoffice.table;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.cartok.quarkus.tutorial.backoffice.api.TablesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiTable;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

public class TablesResource implements TablesApi {
  private final TableMapper mapper;

  @Inject
  public TablesResource(TableMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  @Transactional
  public Response deleteTable(Long tableId) {
    final boolean success = TableEntity.deleteById(tableId);
    if (!success) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().build();
  }

  @Override
  @Transactional
  public Response getTable(Long tableId) {
    final Optional<TableEntity> tableEntity = TableEntity.findByIdOptional(tableId);
    if (tableEntity.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    } else {
      return Response.ok(mapper.mapToDto(tableEntity.get())).build();
    }
  }

  @Override
  @Transactional
  public Response getTables() {
    final List<TableEntity> tableEntities = TableEntity.listAll();
    return Response.ok(tableEntities.stream().map(mapper::mapToDto).toList())
      .build();
  }

  @Override
  @Transactional
  public Response postTable(ApiTable apiTable) {
    final TableEntity tableEntity = new TableEntity();
    mapper.mapToEntity(apiTable, tableEntity);
    tableEntity.persist();
    return Response.created(URI.create("/tables/" + tableEntity.id)).build();
  }

  @Override
  @Transactional
  public Response putTable(Long tableId, ApiTable apiTable) {
    final Optional<TableEntity> existingTable = TableEntity.findByIdOptional(tableId);
    if (existingTable.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    final TableEntity tableEntity = existingTable.get();
    mapper.mapToEntity(apiTable, tableEntity);
    return Response.ok().build();
  }
}
