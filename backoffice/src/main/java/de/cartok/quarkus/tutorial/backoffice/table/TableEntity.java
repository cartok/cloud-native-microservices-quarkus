package de.cartok.quarkus.tutorial.backoffice.table;

import de.cartok.quarkus.tutorial.backoffice.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity()
@Table(name = "restaurant_table", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"name"})
})
@Valid
public class TableEntity extends BaseEntity {
  @NotNull
  public String name;

  @NotNull
  @PositiveOrZero
  public Integer seatCount;

  @NotNull
  public Boolean active;
}
