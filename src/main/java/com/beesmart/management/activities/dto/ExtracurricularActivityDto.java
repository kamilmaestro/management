package com.beesmart.management.activities.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public final class ExtracurricularActivityDto {

  private final UUID id;
  private final String name;
  private final String description;
  private final String type;
  private final String location;
  private final UUID teacherId;
  private final BigDecimal price;
  private final short durationInMinutes;
  private final List<Instant> availableTerms;

  public ExtracurricularActivityDto(UUID id, String name, String description, String type, String location, UUID teacherId,
                                    BigDecimal price, short durationInMinutes, List<Instant> availableTerms) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.type = type;
    this.location = location;
    this.teacherId = teacherId;
    this.price = price;
    this.durationInMinutes = durationInMinutes;
    this.availableTerms = availableTerms;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getType() {
    return type;
  }

  public String getLocation() {
    return location;
  }

  public UUID getTeacherId() {
    return teacherId;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public short getDurationInMinutes() {
    return durationInMinutes;
  }

  public List<Instant> getAvailableTerms() {
    return availableTerms;
  }
}
