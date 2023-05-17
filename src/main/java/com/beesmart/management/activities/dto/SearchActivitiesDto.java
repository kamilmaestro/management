package com.beesmart.management.activities.dto;

import java.math.BigDecimal;

public class SearchActivitiesDto {

  private String name;
  private BigDecimal priceFrom;
  private BigDecimal priceTo;
  private TypeDto type;

  public SearchActivitiesDto(String name, BigDecimal priceFrom, BigDecimal priceTo, TypeDto type) {
    this.name = name;
    this.priceFrom = priceFrom;
    this.priceTo = priceTo;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getPriceFrom() {
    return priceFrom;
  }

  public BigDecimal getPriceTo() {
    return priceTo;
  }

  public TypeDto getType() {
    return type;
  }

}
