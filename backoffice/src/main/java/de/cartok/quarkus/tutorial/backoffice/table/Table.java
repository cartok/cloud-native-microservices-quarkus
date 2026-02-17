package de.cartok.quarkus.tutorial.backoffice.table;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity()
@jakarta.persistence.Table(name = "sbo_table", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"name"})
})
@Valid
public class Table extends PanacheEntity {
  @NotNull
  public String name;

  @NotNull
  @PositiveOrZero
  public Integer seatCount;

  @NotNull
  public Boolean active;
}
