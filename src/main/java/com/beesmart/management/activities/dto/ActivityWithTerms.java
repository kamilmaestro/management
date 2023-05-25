package com.beesmart.management.activities.dto;

import java.util.List;

public class ActivityWithTerms {

  private final ExtracurricularActivityDto activity;
  private final List<TermDto> terms;
  private final boolean isTeacher;

  public ActivityWithTerms(ExtracurricularActivityDto activity, List<TermDto> terms, boolean isTeacher) {
    this.activity = activity;
    this.terms = terms;
    this.isTeacher = isTeacher;
  }

  public ExtracurricularActivityDto getActivity() {
    return activity;
  }

  public List<TermDto> getTerms() {
    return terms;
  }

  public boolean isTeacher() {
    return isTeacher;
  }

}
