package com.odod.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import com.odod.dto.GalleryDto;
import com.odod.service.GalleryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;

@Api(value = "GalleryController")
@Controller
@AllArgsConstructor
public class GalleryController {

  static final Logger logger = LoggerFactory.getLogger(GalleryController.class);

  @Autowired
  private GalleryService galleryService;

  @ApiOperation(value = "테스트 이미지 추가 페이지", tags = "테스트 이미지 관리")
  @RequestMapping(value = "/gallery", method = RequestMethod.GET)
  public String galleryTestView() {

    return "thymeleaf/gallery";
  }

  @ApiOperation(value = "테스트 이미지 추가", tags = "테스트 이미지 관리")
  @RequestMapping(value = "/gallery", method = RequestMethod.POST)
  public String gallerySave(GalleryDto galleryDto, MultipartFile file) throws IOException {

    galleryService.save(file, galleryDto);

    return "redirect:/gallery";
  }
}
