package de.cartok.quarkus.tutorial.backoffice;

import de.cartok.quarkus.tutorial.backoffice.api.model.Table;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TablesService {
  public Table get() {
    return new Table().name("fisch");
  }
}
