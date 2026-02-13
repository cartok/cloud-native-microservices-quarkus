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
  private final TableRepository tableRepository;

  @Inject
  public TableResource(TableMapper mapper, TableRepository tableRepository) {
    this.mapper = mapper;
    this.tableRepository = tableRepository;
  }

  @Override
  @Transactional
  public Response deleteTable(Long tableId) {
    final boolean success = tableRepository.deleteById(tableId);
    if (!success) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().build();
  }

  @Override
  @Transactional
  public Response getTable(Long tableId) {
    final Optional<TableEntity> tableEntity = tableRepository.findByIdOptional(tableId);
    if (tableEntity.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    } else {
      return Response.ok(mapper.mapToDto(tableEntity.get())).build();
    }
  }

  @Override
  @Transactional
  public Response getTables() {
    final List<TableEntity> tableEntities = tableRepository.listAll();
    return Response.ok(tableEntities.stream().map(mapper::mapToDto).toList())
      .build();
  }

  @Override
  @Transactional
  public Response postTable(ApiTable apiTable) {
    final TableEntity tableEntity = new TableEntity();
    mapper.mapToEntity(apiTable, tableEntity);
    tableRepository.persist(tableEntity);
    return Response.created(URI.create("/tables/" + tableEntity.id)).build();
  }

  @Override
  @Transactional
  public Response putTable(Long tableId, ApiTable apiTable) {
    final Optional<TableEntity> existingTable = tableRepository.findByIdOptional(tableId);
    if (existingTable.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    final TableEntity tableEntity = existingTable.get();
    mapper.mapToEntity(apiTable, tableEntity);
    return Response.ok().build();
  }
}
