package de.cartok.quarkus.tutorial.backoffice.table;

import java.util.List;

import de.cartok.quarkus.tutorial.backoffice.api.model.ApiTable;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TableService {
  private final TableMapper mapper;

  @Inject
  public TableService(TableMapper mapper) {
    this.mapper = mapper;
  }

  @WithTransaction
  public Uni<Void> deleteTable(Long id) {
    return Table.deleteById(id)
      .onItem().transform(success -> {
        if (!success) {
          throw new NotFoundException();
        }
        return true;
      })
      .replaceWithVoid()
      ;
  }

  @WithSession
  public Uni<ApiTable> getTable(Long id) {
    return Table.<Table>findById(id)
      .onItem().ifNull().failWith(NotFoundException::new)
      .map(mapper::mapToDto)
      ;
  }

  @WithSession
  public Uni<List<ApiTable>> getTables() {
    return Table.<Table>listAll()
      .map(items -> items.stream().map(mapper::mapToDto).toList())
      ;
  }

  @WithTransaction
  public Uni<Void> postTable(ApiTable apiTable) {
    final Table table = new Table();
    mapper.mapToEntity(apiTable, table);
    return table.persist()
      .replaceWithVoid()
      ;
  }

  @WithTransaction
  public Uni<Void> putTable(Long id, ApiTable apiItem) {
    return Table.<Table>findById(id)
      .onItem().ifNull().failWith(NotFoundException::new)
      .flatMap(oldItem -> {
        mapper.mapToEntity(apiItem, oldItem);
        return Uni.createFrom().voidItem();
      })
      ;
  }
}
