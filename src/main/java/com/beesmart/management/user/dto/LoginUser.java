package com.beesmart.management.user.dto;

public class LoginUser {

  private final String login;
  private final String password;

  public LoginUser(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

}
