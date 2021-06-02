package com.odod.controller;

import java.util.HashMap;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.odod.security.JwtUserDetailsService;
import com.odod.security.UserRequest;
import com.odod.service.UserService;
import com.odod.user.User;
import com.odod.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "UserController")
@RestController
@RequestMapping("/api/user")
public class UserController {

  static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private JwtUserDetailsService userDetailService;

  @ApiOperation(value = "회원 가입 or 로그인", tags = "회원 정보 관리")
  @RequestMapping(value = "/auth", method = RequestMethod.POST)
  public ResponseEntity<HashMap<String, Object>> saveUser(@RequestBody UserRequest userReq) {
    HashMap<String, Object> result = new HashMap<String, Object>();
    String message;

    Optional<User> user = userService.findUserByEmail(userReq.getEmail());

    if (user.isPresent()) {
      message = "User Login .";

    } else {
      message = "User Register .";
      userService.save(User.builder().email(userReq.getEmail()).name(userReq.getName()).build());
    }

    UserDetails userDetails = userDetailService.loadUserByUsername(userReq.getEmail());

    result.put("token", jwtTokenUtil.generateToken(userDetails));
    result.put("message", message);
    result.put("resultCode", 1L);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @ApiOperation(value = "회원 유효성 체크", tags = "회원 정보 관리")
  @RequestMapping(value = "/exist/{email}", method = RequestMethod.GET)
  public ResponseEntity<HashMap<String, Object>> findUserByEmail(@PathVariable String email) {
    HashMap<String, Object> result = new HashMap<String, Object>();

    Optional<User> user = userService.findUserByEmail(email);

    result.put("userCheck", user.isPresent() ? true : false);
    result.put("resultCode", 1L);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
