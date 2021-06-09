package com.odod.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.odod.dto.GalleryDto;
import com.odod.service.GalleryService;
import com.odod.service.S3Service;
import com.odod.util.CommonConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.HashMap;

@Api(value = "GalleryRestController")
@RestController
@AllArgsConstructor
@RequestMapping("/api/gallery")
public class GalleryRestController {

  static final Logger logger = LoggerFactory.getLogger(GalleryRestController.class);

  @Autowired
  private GalleryService galleryService;

  @ApiOperation(value = "이미지 추가", tags = "이미지 관리")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public ResponseEntity<HashMap<String, Object>> gallerySave(GalleryDto galleryDto, MultipartFile file) throws IOException {
    HashMap<String, Object> result = new HashMap<String, Object>();

    galleryService.save(file, galleryDto);

    result.put("data", galleryDto);
    result.put(CommonConstant.Response.API_RESULT_CODE_KEY, CommonConstant.Response.API_RESULT_CODE_SUCC);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}