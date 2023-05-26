package com.beesmart.management.activities.infrastructure;

import com.beesmart.management.activities.domain.ExtracurricularService;
import com.beesmart.management.activities.dto.*;
import com.beesmart.management.user.dto.LoggedUser;
import com.beesmart.management.user.dto.UserRoleDto;
import com.beesmart.management.utils.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/extracurricular")
@RestController
class ExtracurricularController {

  private ExtracurricularService service;

  @Autowired
  ExtracurricularController(ExtracurricularService service) {
    this.service = service;
  }

  @PostMapping(value = "/")
  ResponseEntity<ExtracurricularActivityDto> addActivityConfiguration(@RequestBody NewActivityDto newActivity) {
    return ResponseEntity.ok(service.configureNewActivity(newActivity));
  }

  @PutMapping(value = "/join/")
  ResponseEntity<Void> joinActivity(@RequestBody JoinActivity joinActivity) {
    service.joinActivity(joinActivity);
    return ResponseEntity.ok().build();
  }

  @PostMapping(value = "/search/")
  ResponseEntity<List<ExtracurricularActivityDto>> search(@RequestBody SearchActivitiesDto search) {
    return ResponseEntity.ok(service.search(search));
  }

  @GetMapping(value = "/")
  ResponseEntity<ActivityTermsList> getActivities() {
    return ResponseEntity.ok(service.getUserActivities());
  }

}
