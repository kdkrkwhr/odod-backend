package com.odod.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.odod.dto.PositionResponseDto;
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
  public ResponseEntity<Map<String, Object>> positionRegister(@RequestBody PositionResponseDto position) {
    return new ResponseEntity<>(service.insertPositionData(position), HttpStatus.OK);
  }
}
