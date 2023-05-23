package com.beesmart.management.user.infrastructure;

import com.beesmart.management.activities.domain.ExtracurricularService;
import com.beesmart.management.user.domain.UserService;
import com.beesmart.management.user.dto.LoggedUser;
import com.beesmart.management.user.dto.LoginUser;
import com.beesmart.management.user.dto.RegisterUser;
import com.beesmart.management.utils.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
class UserController {

  private UserService service;

  @Autowired
  UserController(UserService service) {
    this.service = service;
  }

  @PostMapping(value = "/register")
  ResponseEntity<LoggedUser> register(@RequestBody RegisterUser user) {
    return ResponseEntity.ok(service.register(user));
  }

  @PostMapping(value = "/login")
  ResponseEntity<LoggedUser> login(@RequestBody LoginUser user) {
    return ResponseEntity.ok(service.login(user));
  }

  @PostMapping(value = "/logout")
  ResponseEntity<Void> logout() {
    LoggedInUser.logOut();
    return ResponseEntity.ok().build();
  }

}
