package com.beesmart.management.activities.domain;

import com.beesmart.management.activities.dto.ExtracurricularActivityDto;
import com.beesmart.management.activities.dto.JoinActivity;
import com.beesmart.management.activities.dto.NewActivityDto;
import com.beesmart.management.activities.dto.SearchActivitiesDto;
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
    final List<ExtracurricularActivityConfiguration> searched = search.getType() != null ?
        extracurricularRepository.search(search.getName(), search.getPriceFrom(), search.getPriceTo(), search.getType())
        : extracurricularRepository.search(search.getName(), search.getPriceFrom(), search.getPriceTo());

    return searched
        .stream()
        .map(ExtracurricularActivityConfiguration::dto)
        .collect(Collectors.toList());
  }

  public List<ExtracurricularActivityDto> getUserActivities() {
    return null;
  }

}
