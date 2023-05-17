package com.beesmart.management.activities.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "activity_term")
class ActivityTerm {

  @Id
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
  private UUID id;
  private Instant startAt;
  private Instant endAt;
  private UUID activityId;
  private UUID studentId;

  private ActivityTerm() {
  }

  private ActivityTerm(UUID id, Instant startAt, Instant endAt, UUID activityId, UUID studentId) {
    this.id = id;
    this.startAt = startAt;
    this.endAt = endAt;
    this.activityId = activityId;
    this.studentId = studentId;
  }

  static ActivityTerm joinTerm(Instant startAt, ExtracurricularActivityConfiguration configuration, UUID studentId) {
    final Instant endAt = startAt.plus(configuration.getDurationInMinutes(), ChronoUnit.MINUTES);
    return new ActivityTerm(UUID.randomUUID(), startAt, endAt, configuration.getId(), studentId);
  }

}
