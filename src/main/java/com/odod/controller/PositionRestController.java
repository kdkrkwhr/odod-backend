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
import com.odod.dto.PositionRequestDto;
import com.odod.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "PositionRestController")
@RestController
@RequestMapping("/api/position")
public class PositionRestController {

  static final Logger logger = LoggerFactory.getLogger(PositionRestController.class);

  @Autowired
  private PositionService service;

  @ApiOperation(value = "위치 데이터 추가", tags = "위치 데이터")
  @RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<Map<String, Object>> insertPositionData(@RequestBody PositionRequestDto position) {
    return new ResponseEntity<>(service.insertPositionData(position), HttpStatus.OK);
  }

  @ApiOperation(value = "위치 데이터 조회", tags = "위치 데이터")
  @RequestMapping(value = "/select", method = RequestMethod.GET)
  public ResponseEntity<?> selectPositionData(HttpServletRequest request) {
    String userId = request.getParameter("userId") == null ? "" : request.getParameter("userId");
    return new ResponseEntity<>(service.selectPositionData(userId), HttpStatus.OK);
  }
}
