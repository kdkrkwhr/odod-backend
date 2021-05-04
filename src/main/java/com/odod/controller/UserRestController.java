package com.odod.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.odod.dto.UserRequestDto;
import com.odod.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "UserRestController")
@RestController
@RequestMapping("/api/user")
public class UserRestController {

  static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

  @Autowired
  private UserService service;

  @ApiOperation(value = "회원 목록 조회 API", tags = "회원 관리")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public ResponseEntity<Map<String, Object>> userList(HttpServletRequest req) {
    return new ResponseEntity<>(service.userList(), HttpStatus.OK);
  }

  @ApiOperation(value = "회원 가입 API", tags = "회원 관리")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public ResponseEntity<Map<String, Object>> userRegister(@RequestBody UserRequestDto uesr) {
    return new ResponseEntity<>(service.userRegister(uesr), HttpStatus.OK);
  }
}
