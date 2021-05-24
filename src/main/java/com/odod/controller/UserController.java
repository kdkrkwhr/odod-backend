package com.odod.controller;

import java.util.HashMap;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.odod.security.UserRequest;
import com.odod.service.UserService;
import com.odod.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "UserController")
@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  UserService userService;

  @ApiOperation(value = "회원 가입", tags = "회원 정보 관리")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public ResponseEntity<?> saveUser(@RequestBody UserRequest userReq) {
    HashMap<String, Object> result = new HashMap<String, Object>();

    userService.save(User.builder().email(userReq.getEmail()).name(userReq.getName()).build());

    result.put("resultCode", 1L);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @ApiOperation(value = "회원 유효성 체크", tags = "회원 정보 관리")
  @RequestMapping(value = "/exist/{email}", method = RequestMethod.GET)
  public ResponseEntity<?> findUserByEmail(@PathVariable String email) {
    HashMap<String, Object> result = new HashMap<String, Object>();

    Optional<User> user = userService.findUserByEmail(email);

    result.put("userCheck", user.isPresent() ? true : false);
    result.put("resultCode", 1L);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
