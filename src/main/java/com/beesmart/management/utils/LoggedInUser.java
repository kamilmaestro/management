package com.beesmart.management.utils;

import java.util.UUID;

public final class LoggedInUser {

  private static UUID savedLoggedInUserId;

  public static UUID saveLoggedInUserId(UUID loggedInUserId) {
    savedLoggedInUserId = loggedInUserId;
    return savedLoggedInUserId;
  }

  public static UUID getLoggedInUserId() {
    return savedLoggedInUserId;
  }

}
