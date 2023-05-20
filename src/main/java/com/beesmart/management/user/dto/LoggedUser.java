package com.beesmart.management.user.dto;

import java.util.UUID;

public class LoggedUser {

  public enum Role {
    STUDENT,
    TEACHER
  }

  UUID id;
  Role role;

  public LoggedUser(UUID id, Role role) {
    this.id = id;
    this.role = role;
  }

  public UUID getId() {
    return id;
  }

  public Role getRole() {
    return role;
  }

}
