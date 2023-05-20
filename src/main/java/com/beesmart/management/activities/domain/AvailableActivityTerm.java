package com.beesmart.management.activities.domain;

import java.time.Instant;
import java.util.Objects;


class AvailableActivityTerm {

  private Instant date;

  AvailableActivityTerm(Instant date) {
    this.date = date;
  }

  Instant getDate() {
    return date;
  }

  boolean equals(Instant date) {
    return Objects.equals(this.date, date);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AvailableActivityTerm that = (AvailableActivityTerm) o;
    return Objects.equals(date, that.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date);
  }
}
