package de.cartok.quarkus.tutorial.backoffice.table;

import java.util.List;
import java.util.concurrent.CompletionStage;

import de.cartok.quarkus.tutorial.backoffice.api.TablesApi;
import de.cartok.quarkus.tutorial.backoffice.api.model.ApiTable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TableResource implements TablesApi {
  private final TableService service;

  @Inject
  public TableResource(TableService service) {
    this.service = service;
  }

  @Override
  public CompletionStage<Void> deleteTable(Long id) {
    return service.deleteTable(id).subscribeAsCompletionStage();
  }

  @Override
  public CompletionStage<ApiTable> getTable(Long id) {
    return service.getTable(id).subscribeAsCompletionStage();
  }

  @Override
  public CompletionStage<List<ApiTable>> getTables() {
    return service.getTables().subscribeAsCompletionStage();
  }

  @Override
  public CompletionStage<Void> postTable(ApiTable apiItem) {
    return service.postTable(apiItem).subscribeAsCompletionStage();
  }

  @Override
  public CompletionStage<Void> putTable(Long id, ApiTable apiItem) {
    return service.putTable(id, apiItem).subscribeAsCompletionStage();
  }
}
