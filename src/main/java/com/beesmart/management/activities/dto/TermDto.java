package com.beesmart.management.activities.dto;

import java.time.Instant;
import java.util.UUID;

public class TermDto {
  private final UUID id;
  private final Instant startAt;
  private final Instant endAt;
  private final UUID activityId;
  private final UUID studentId;

  public TermDto(UUID id, Instant startAt, Instant endAt, UUID activityId, UUID studentId) {
    this.id = id;
    this.startAt = startAt;
    this.endAt = endAt;
    this.activityId = activityId;
    this.studentId = studentId;
  }

  public UUID getId() {
    return id;
  }

  public Instant getStartAt() {
    return startAt;
  }

  public Instant getEndAt() {
    return endAt;
  }

  public UUID getActivityId() {
    return activityId;
  }

  public UUID getStudentId() {
    return studentId;
  }
}
