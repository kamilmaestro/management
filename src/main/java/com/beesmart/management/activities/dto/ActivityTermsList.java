package com.beesmart.management.activities.dto;

import java.util.List;

public class ActivityTermsList {

  private final List<ActivityWithTerms> activities;
  private final boolean isTeacher;

  public ActivityTermsList(List<ActivityWithTerms> activities, boolean isTeacher) {
    this.activities = activities;
    this.isTeacher = isTeacher;
  }

  public List<ActivityWithTerms> getActivities() {
    return activities;
  }

  public boolean isTeacher() {
    return isTeacher;
  }

}
