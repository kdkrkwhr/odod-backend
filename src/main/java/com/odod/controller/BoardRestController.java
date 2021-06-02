package com.odod.controller;

import java.util.HashMap;
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
import com.odod.dto.BoardRequestDto;
import com.odod.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "BoardRestController")
@RestController
@RequestMapping("/api/board")
public class BoardRestController {

  static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);

  @Autowired
  private BoardService service;

  @ApiOperation(value = "이미지 게시판 추가", tags = "이미지 게시판 관리")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public ResponseEntity<Map<String, Object>> insertBoardData(@RequestBody BoardRequestDto board) {
    HashMap<String, Object> result = new HashMap<String, Object>();

    result.put("resultCode", service.save(board));

    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
