package com.beesmart.management.utils;

import com.beesmart.management.user.dto.LoggedUser;

import java.util.UUID;

public final class LoggedInUser {

  private static LoggedUser savedLoggedInUser;

  public static LoggedUser saveLoggedInUser(LoggedUser user) {
    savedLoggedInUser = user;
    return savedLoggedInUser;
  }

  public static LoggedUser getLoggedInUser() {
    return savedLoggedInUser;
  }

}
