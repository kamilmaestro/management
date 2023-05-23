package com.beesmart.management.activities.domain;

import com.beesmart.management.activities.dto.ExtracurricularActivityDto;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.annotations.Type;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "extracurricular_activity_configuration")
//@Type(name = "json", typeClass = JsonType.class)
class ExtracurricularActivityConfiguration {

  enum ActivityType {
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
  private ActivityType type;
  private String location;
  private UUID teacherId;
  private BigDecimal price;
  private short durationInMinutes;

  @Convert(converter = JsonListConverter.class)
  private List<AvailableActivityTerm> availableTerms;

  ExtracurricularActivityConfiguration() {
  }

  ExtracurricularActivityConfiguration(String name, String description, ActivityType type, String location, UUID teacherId,
                                       BigDecimal price, short durationInMinutes, List<Instant> availableTerms) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.description = description;
    this.type = type;
    this.location = location;
    this.teacherId = teacherId;
    this.price = price;
    this.durationInMinutes = durationInMinutes;
    this.availableTerms = availableTerms != null ? availableTerms.stream()
        .map(AvailableActivityTerm::new)
        .collect(Collectors.toList())
        : new ArrayList<>();
  }

  ExtracurricularActivityDto dto() {
    final List<Instant> availableTerms = this.availableTerms.stream()
        .map(AvailableActivityTerm::getDate)
        .collect(Collectors.toList());
    return new ExtracurricularActivityDto(
        this.id, this.name, this.description, this.type.name(), this.location, this.teacherId, price,
        this.durationInMinutes, availableTerms
    );
  }

  void checkIfContainsAvailableTermFor(Instant termDate) {
    boolean containsTerm = availableTerms.stream()
        .anyMatch(term -> term.equals(termDate));
    if (!containsTerm) {
      throw new IllegalStateException();
    }
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

  ActivityType getType() {
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

  BigDecimal getPrice() {
    return price;
  }

  List<AvailableActivityTerm> getAvailableTerms() {
    return availableTerms;
  }
}
