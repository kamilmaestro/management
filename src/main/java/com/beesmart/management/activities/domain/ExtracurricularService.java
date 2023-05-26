package com.beesmart.management.activities.domain;

import com.beesmart.management.activities.dto.*;
import com.beesmart.management.user.dto.LoggedUser;
import com.beesmart.management.user.dto.UserRoleDto;
import com.beesmart.management.utils.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExtracurricularService {

  ExtracurricularRepository extracurricularRepository;
  ActivityTermRepository activityTermRepository;

  @Autowired
  public ExtracurricularService(ExtracurricularRepository extracurricularRepository,
                                ActivityTermRepository activityTermRepository) {
    this.extracurricularRepository = extracurricularRepository;
    this.activityTermRepository = activityTermRepository;
  }

  public ExtracurricularActivityDto configureNewActivity(NewActivityDto newActivity) {
    final UUID loggedInUserId = LoggedInUser.getLoggedInUser().getId();
    final ExtracurricularActivityConfiguration activity = new ExtracurricularActivityConfiguration(
        newActivity.getName(), newActivity.getDescription(),
        ExtracurricularActivityConfiguration.ActivityType.valueOf(newActivity.getType().name()),
        newActivity.getLocation(), loggedInUserId, newActivity.getPrice(), newActivity.getDurationInMinutes(),
        newActivity.getAvailableTerms()
    );
    return extracurricularRepository.save(activity)
        .dto();
  }

  public void joinActivity(JoinActivity joinActivity) {
    final ExtracurricularActivityConfiguration configuration = extracurricularRepository
        .findById(joinActivity.getActivityConfigurationId())
        .orElseThrow(IllegalStateException::new);
    configuration.checkIfContainsAvailableTermFor(joinActivity.getStartActivityAt());
    final ActivityTerm activityTerm = ActivityTerm.joinTerm(
        joinActivity.getStartActivityAt(), configuration, LoggedInUser.getLoggedInUser().getId()
    );
    activityTermRepository.save(activityTerm);
  }

  public List<ExtracurricularActivityDto> search(SearchActivitiesDto search) {
    BigDecimal priceFrom = Optional.ofNullable(search.getPriceFrom()).orElse(BigDecimal.ZERO);
    BigDecimal priceTo = Optional.ofNullable(search.getPriceTo()).orElse(new BigDecimal(1000));
    String name = Optional.ofNullable(search.getName()).orElse("");
    final List<ExtracurricularActivityConfiguration> searched = search.getType() != null ?
        extracurricularRepository.search(name, priceFrom, priceTo, search.getType())
        : extracurricularRepository.search(name, priceFrom, priceTo);

    return searched
        .stream()
        .map(ExtracurricularActivityConfiguration::dto)
        .collect(Collectors.toList());
  }

  public ActivityTermsList getUserActivities() {
    LoggedUser loggedInUser = LoggedInUser.getLoggedInUser();
    if (UserRoleDto.TEACHER == loggedInUser.getRole()) {
      return new ActivityTermsList(getTeacherActivities(loggedInUser.getId()), true);
    } else {
      return new ActivityTermsList(getStudentActivities(loggedInUser.getId()), false);
    }
  }

  private List<ActivityWithTerms> getTeacherActivities(UUID teacherId) {
    return extracurricularRepository.getTeacherActivities(teacherId).stream()
        .map(this::getActivityWithAllTermsForTeacher)
        .toList();
  }

  private ActivityWithTerms getActivityWithAllTermsForTeacher(ExtracurricularActivityConfiguration activity) {
    ExtracurricularActivityDto dto = activity.dto();
    List<TermDto> terms = activityTermRepository.findByActivityId(activity.getId()).stream()
        .map(ActivityTerm::dto).toList();
    return new ActivityWithTerms(dto, terms);
  }

  private List<ActivityWithTerms> getStudentActivities(UUID studentId) {
    List<ActivityTerm> terms = activityTermRepository.findByStudentId(studentId);
    List<UUID> activitiesIds = terms.stream().map(ActivityTerm::getActivityId)
        .toList();

    return extracurricularRepository.findAllById(activitiesIds).stream()
        .map(activity -> {
          List<ActivityTerm> activityTerms = terms.stream()
              .filter(term -> term.getActivityId().equals(activity.getId()))
              .toList();
          return getActivityWithSpecifiedTermsForStudent(activity, activityTerms);
        }).toList();
  }

  private ActivityWithTerms getActivityWithSpecifiedTermsForStudent(ExtracurricularActivityConfiguration activity,
                                                                    List<ActivityTerm> terms) {
    List<TermDto> termDtos = terms.stream().map(ActivityTerm::dto).toList();
    return new ActivityWithTerms(activity.dto(), termDtos);
  }

}
