package com.beesmart.management.user.dto;

import java.util.UUID;

public class LoggedUser {

  UUID id;
  UserRoleDto role;

  public LoggedUser(UUID id, UserRoleDto role) {
    this.id = id;
    this.role = role;
  }

  public UUID getId() {
    return id;
  }

  public UserRoleDto getRole() {
    return role;
  }

}
