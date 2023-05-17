package com.beesmart.management.activities.domain;

import com.beesmart.management.activities.dto.ExtracurricularActivityDto;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "extracurricular_activity_configuration")
class ExtracurricularActivityConfiguration {

  enum Type {
    IN_PERSON,
    ONLINE
  }

  @Id
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
  private UUID id;
  private String name;
  private String description;
  @Enumerated(EnumType.STRING)
  private Type type;
  private String location;
  private UUID teacherId;
  private BigDecimal price;
  private short durationInMinutes;

  ExtracurricularActivityConfiguration() {
  }

  ExtracurricularActivityConfiguration(String name, String description, Type type, String location,
                                       UUID teacherId, BigDecimal price, short durationInMinutes) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.description = description;
    this.type = type;
    this.location = location;
    this.teacherId = teacherId;
    this.price = price;
    this.durationInMinutes = durationInMinutes;
  }

  ExtracurricularActivityDto dto() {
    return new ExtracurricularActivityDto(
        this.id, this.name, this.description, this.type.name(), this.location, this.teacherId, price, this.durationInMinutes
    );
  }

   UUID getId() {
    return id;
  }

   String getName() {
    return name;
  }

   String getDescription() {
    return description;
  }

   Type getType() {
    return type;
  }

   String getLocation() {
    return location;
  }

   UUID getTeacherId() {
    return teacherId;
  }

   short getDurationInMinutes() {
    return durationInMinutes;
  }

}
