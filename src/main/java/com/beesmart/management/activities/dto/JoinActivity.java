package com.beesmart.management.activities.dto;

import java.time.Instant;
import java.util.UUID;

public class JoinActivity {

  private Instant startActivityAt;
  private UUID activityConfigurationId;

  public JoinActivity(Instant startActivityAt, UUID activityConfigurationId) {
    this.startActivityAt = startActivityAt;
    this.activityConfigurationId = activityConfigurationId;
  }

  public Instant getStartActivityAt() {
    return startActivityAt;
  }

  public UUID getActivityConfigurationId() {
    return activityConfigurationId;
  }

}
