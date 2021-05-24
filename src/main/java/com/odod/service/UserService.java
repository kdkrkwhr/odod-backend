package com.odod.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.odod.user.Role;
import com.odod.user.User;
import com.odod.user.UserRepository;

@Service
public class UserService {

  private UserRepository repository;

  @Autowired
  public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
  }

  public int save(User user) {
    repository.save(User.builder().email(user.getEmail()).name(user.getName())
        .picture(user.getPicture()).role(Role.USER).build());
    return 1;
  }

  public Optional<User> findUserByEmail(String email) {
    Optional<User> aleadyUser = repository.findByEmail(email);
    return aleadyUser;
  }
}
