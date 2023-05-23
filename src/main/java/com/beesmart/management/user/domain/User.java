package com.beesmart.management.user.domain;

import com.beesmart.management.user.dto.LoggedUser;
import com.beesmart.management.user.dto.UserRoleDto;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "users")
class User {

  enum Role {
    STUDENT,
    TEACHER
  }

  @Id
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
  private UUID id;
  @Enumerated(EnumType.STRING)
  private Role role;
  private String login;
  private String password;

  User() {
  }

  User(Role role, String login, String password) {
    this.id = UUID.randomUUID();
    this.role = role;
    this.login = login;
    this.password = password;
  }

  void checkPassword(String password) {
    if (password == null || !password.equals(this.password)) {
      throw new IllegalStateException("Incorrect password");
    }
  }

  LoggedUser dto() {
    return new LoggedUser(id, UserRoleDto.valueOf(role.name()));
  }

  public UUID getId() {
    return id;
  }

  public Role getRole() {
    return role;
  }

  public String getLogin() {
    return login;
  }

}
