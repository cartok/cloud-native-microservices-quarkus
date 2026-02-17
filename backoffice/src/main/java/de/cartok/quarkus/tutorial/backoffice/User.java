package de.cartok.quarkus.tutorial.backoffice;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sbo_user")
@UserDefinition
public class User extends PanacheEntity {
  @Username
  public String username;

  @Password
  public String password;

  @Roles
  public String role;
}
