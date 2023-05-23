package com.beesmart.management.utils;

import com.beesmart.management.user.dto.LoggedUser;

import java.util.UUID;

public final class LoggedInUser {

  private static LoggedUser savedLoggedInUser;

  public static void saveLoggedInUser(LoggedUser user) {
    savedLoggedInUser = user;
  }

  public static LoggedUser getLoggedInUser() {
    return savedLoggedInUser;
  }

  public static void logOut() {
    savedLoggedInUser = null;
  }

}
