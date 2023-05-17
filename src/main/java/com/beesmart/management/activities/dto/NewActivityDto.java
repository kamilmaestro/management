package com.beesmart.management.activities.dto;

import java.math.BigDecimal;

public class NewActivityDto {

  private String name;
  private String description;
  private TypeDto type;
  private String location;
  private BigDecimal price;
  private short durationInMinutes;

  public NewActivityDto(String name, String description, TypeDto type, String location, BigDecimal price, short durationInMinutes) {
    this.name = name;
    this.description = description;
    this.type = type;
    this.location = location;
    this.price = price;
    this.durationInMinutes = durationInMinutes;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public TypeDto getType() {
    return type;
  }

  public String getLocation() {
    return location;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public short getDurationInMinutes() {
    return durationInMinutes;
  }

}
