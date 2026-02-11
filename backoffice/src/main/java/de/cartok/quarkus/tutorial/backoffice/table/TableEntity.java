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
  private String name;

  @NotNull
  @PositiveOrZero
  private Integer seatCount;

  @NotNull
  private Boolean active;

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Integer getSeatCount() {
    return seatCount;
  }

  public void setSeatCount(Integer seatCount) {
    this.seatCount = seatCount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
