package de.cartok.quarkus.tutorial.backoffice.table;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TableRepository implements PanacheRepository<TableEntity> {
}
