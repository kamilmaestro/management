package com.beesmart.management.user.dto;

public class RegisterUser {

  private final String login;
  private final String password;
  private final UserRoleDto role;

  public RegisterUser(String login, String password, UserRoleDto role) {
    this.login = login;
    this.password = password;
    this.role = role;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public UserRoleDto getRole() {
    return role;
  }

}
