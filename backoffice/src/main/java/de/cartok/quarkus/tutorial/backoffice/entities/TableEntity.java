package de.cartok.quarkus.tutorial.backoffice.entities;

import jakarta.persistence.Entity;

@Entity
public class TableEntity extends BaseEntity {
  private String name;
  private Integer seatCount;
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
