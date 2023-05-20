package com.beesmart.management.user.domain;

import java.util.UUID;

class User {

  enum Role {
    STUDENT,
    TEACHER
  }

  UUID id;
  Role role;

  User() {
  }

  User(UUID id, Role role) {
    this.id = id;
    this.role = role;
  }

}
