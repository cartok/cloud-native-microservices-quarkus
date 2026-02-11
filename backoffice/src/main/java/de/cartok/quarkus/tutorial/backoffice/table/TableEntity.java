package de.cartok.quarkus.tutorial.backoffice.table;

import de.cartok.quarkus.tutorial.backoffice.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity()
@Table(name = "restaurant_table", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"name"})
})
public class TableEntity extends BaseEntity {
  @NotNull
  //  @Column(nullable = false)
  private String name;

  @NotNull
  //  @Column(nullable = false)
  private Integer seatCount;

  @NotNull
  //  @Column(nullable = false)
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
