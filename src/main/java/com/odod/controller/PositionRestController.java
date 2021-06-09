package com.odod.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.odod.dto.PositionRequestDto;
import com.odod.dto.SearchPositionDto;
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

  @CrossOrigin
  @ApiOperation(value = "위치 데이터 추가", tags = "위치 데이터")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public ResponseEntity<Map<String, Object>> insertPositionData(@RequestBody PositionRequestDto position) {
    HashMap<String, Object> result = new HashMap<String, Object>();

    result.put("resultCode", service.insertPositionData(position));

    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @CrossOrigin
  @ApiOperation(value = "위치 데이터 조회", tags = "위치 데이터")
  @RequestMapping(value = "/select", method = RequestMethod.GET)
  public ResponseEntity<Object> selectPositionData(@RequestBody SearchPositionDto search) throws IOException {
    HashMap<String, Object> result = new HashMap<String, Object>();

    result.put("data", service.selectPositionData(search));
    result.put("resultCode", 1L);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
