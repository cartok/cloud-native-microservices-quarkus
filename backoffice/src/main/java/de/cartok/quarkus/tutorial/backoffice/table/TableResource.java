package de.cartok.quarkus.tutorial.backoffice.table;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.cartok.quarkus.tutorial.backoffice.api.TablesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiTable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class TableResource implements TablesApi {
  private final TableMapper mapper;

  @Inject
  public TableResource(TableMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  @Transactional
  public Response deleteTable(Long tableId) {
    final boolean success = Table.deleteById(tableId);
    if (!success) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().build();
  }

  @Override
  @Transactional
  public Response getTable(Long tableId) {
    final Optional<Table> tableEntity = Table.findByIdOptional(tableId);
    if (tableEntity.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    } else {
      return Response.ok(mapper.mapToDto(tableEntity.get())).build();
    }
  }

  @Override
  @Transactional
  public Response getTables() {
    final List<Table> tableEntities = Table.listAll();
    return Response.ok(tableEntities.stream().map(mapper::mapToDto).toList())
      .build();
  }

  @Override
  @Transactional
  public Response postTable(ApiTable apiTable) {
    final Table table = new Table();
    mapper.mapToEntity(apiTable, table);
    table.persist();
    return Response.created(URI.create("/tables/" + table.id)).build();
  }

  @Override
  @Transactional
  public Response putTable(Long tableId, ApiTable apiTable) {
    final Optional<Table> existingTable = Table.findByIdOptional(tableId);
    if (existingTable.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    final Table table = existingTable.get();
    mapper.mapToEntity(apiTable, table);
    return Response.ok().build();
  }
}
