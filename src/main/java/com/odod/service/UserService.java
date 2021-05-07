package com.odod.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.odod.domain.user.User;
import com.odod.domain.user.UserRepository;
import com.odod.dto.UserRequestDto;
import com.odod.util.CommonConstant;

@Service
public class UserService {

  static final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserRepository repository;

  public Map<String, Object> userList() {
    Map<String, Object> result = new LinkedHashMap<String, Object>();
    List<User> userList = new ArrayList<User>();

    userList = repository.findAll();
    result.put("data", userList);
    result.put(CommonConstant.ResponseUtil.API_RESULT_CODE_KEY, CommonConstant.ResponseUtil.API_RESULT_CODE_SUCC);

    return result;
  }

  public Map<String, Object> userRegister(UserRequestDto dto) {
    Map<String, Object> result = new LinkedHashMap<String, Object>();
    long resultCode = 0;

    try {

      resultCode = CommonConstant.ResponseUtil.API_RESULT_CODE_SUCC;

    } catch (Exception e) {
      logger.error("==== USER DATA INSERT ERROR :: {} ====", e.getMessage());
      resultCode = CommonConstant.ResponseUtil.API_RESULT_CODE_FAIL;
    }

    result.put(CommonConstant.ResponseUtil.API_RESULT_CODE_KEY, resultCode);
    return result;
  }
}
