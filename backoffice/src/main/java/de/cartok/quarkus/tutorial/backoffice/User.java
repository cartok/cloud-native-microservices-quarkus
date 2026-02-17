package de.cartok.quarkus.tutorial.backoffice;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.security.jpa.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sbo_user")
@UserDefinition
public class User extends PanacheEntity {
  @Username
  public String username;

  @Password(PasswordType.MCF)
  public String password;

  @Roles
  public String role;
}
