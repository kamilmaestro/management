package com.beesmart.management.activities.domain;

import com.beesmart.management.activities.dto.ExtracurricularActivityDto;
import com.beesmart.management.activities.dto.JoinActivity;
import com.beesmart.management.activities.dto.NewActivityDto;
import com.beesmart.management.activities.dto.SearchActivitiesDto;
import com.beesmart.management.utils.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    final UUID loggedInUserId = LoggedInUser.getLoggedInUserId();
    final ExtracurricularActivityConfiguration activity = new ExtracurricularActivityConfiguration(
        newActivity.getName(), newActivity.getDescription(),
        ExtracurricularActivityConfiguration.Type.valueOf(newActivity.getType().name()),
        newActivity.getLocation(), loggedInUserId, newActivity.getPrice(), newActivity.getDurationInMinutes()
    );
    return extracurricularRepository.save(activity)
        .dto();
  }

  public void joinActivity(JoinActivity joinActivity) {
    final ExtracurricularActivityConfiguration configuration = extracurricularRepository
        .findById(joinActivity.getActivityConfigurationId())
        .orElseThrow(IllegalStateException::new);
    final ActivityTerm activityTerm = ActivityTerm.joinTerm(
        joinActivity.getStartActivityAt(), configuration, LoggedInUser.getLoggedInUserId()
    );
    activityTermRepository.save(activityTerm);
  }

  public List<ExtracurricularActivityDto> search(SearchActivitiesDto search) {
    return extracurricularRepository.search(search.getName(), search.getPriceFrom(), search.getPriceTo(), search.getType())
        .stream()
        .map(ExtracurricularActivityConfiguration::dto)
        .collect(Collectors.toList());
  }

}
