package com.odod.security;

import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.odod.service.UserService;
import com.odod.user.User;
import com.odod.user.UserRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
    private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> userOptional = userService.findUserByEmail(email);

    User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("user email not found!"));
    return new org.springframework.security.core.userdetails.User(user.getEmail(),
        "1", new ArrayList<>());
  }
}
