package com.beesmart.management.activities.dto;

import java.util.List;

public class ActivityWithTerms {

  private final ExtracurricularActivityDto activity;
  private final List<TermDto> terms;

  public ActivityWithTerms(ExtracurricularActivityDto activity, List<TermDto> terms) {
    this.activity = activity;
    this.terms = terms;
  }

  public ExtracurricularActivityDto getActivity() {
    return activity;
  }

  public List<TermDto> getTerms() {
    return terms;
  }
}
