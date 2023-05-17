package com.beesmart.management.activities.infrastructure;

import com.beesmart.management.activities.domain.ExtracurricularService;
import com.beesmart.management.activities.dto.ExtracurricularActivityDto;
import com.beesmart.management.activities.dto.JoinActivity;
import com.beesmart.management.activities.dto.NewActivityDto;
import com.beesmart.management.utils.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    LoggedInUser.saveLoggedInUserId(UUID.fromString("e0785c90-f25f-11ed-a05b-0242ac120003"));
    return ResponseEntity.ok(service.configureNewActivity(newActivity));
  }

  @PutMapping(value = "/join/")
  ResponseEntity<Void> joinActivity(@RequestBody JoinActivity joinActivity) {
    LoggedInUser.saveLoggedInUserId(UUID.fromString("0da7766f-14d9-4433-8462-ba05113f3b43"));
    service.joinActivity(joinActivity);
    return ResponseEntity.ok().build();
  }

}
