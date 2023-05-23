package com.beesmart.management.user.domain;

import com.beesmart.management.user.dto.LoggedUser;
import com.beesmart.management.user.dto.LoginUser;
import com.beesmart.management.user.dto.RegisterUser;
import com.beesmart.management.user.dto.UserRoleDto;
import com.beesmart.management.utils.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public LoggedUser register(RegisterUser user) {
    userRepository.findByLogin(user.getLogin())
        .ifPresent(u -> {
          throw new IllegalStateException("User with such login already exists");
        });
    final User registered = userRepository.save(
        new User(User.Role.valueOf(user.getRole().name()), user.getLogin(), user.getPassword())
    );
    return registered.dto();
  }

  public LoggedUser login(LoginUser user) {
    final User logged = userRepository.findByLogin(user.getLogin())
        .orElseThrow(() -> new IllegalStateException("User not found"));
    logged.checkPassword(user.getPassword());
    LoggedInUser.saveLoggedInUser(
        new LoggedUser(logged.getId(), UserRoleDto.valueOf(logged.getRole().name()))
    );
    return logged.dto();
  }

}
