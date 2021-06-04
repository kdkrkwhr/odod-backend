package com.odod.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import com.odod.dto.GalleryDto;
import com.odod.service.GalleryService;
import com.odod.service.S3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;

@Api(value = "GalleryController")
@Controller
@AllArgsConstructor
public class GalleryController {

  static final Logger logger = LoggerFactory.getLogger(GalleryController.class);

  @Autowired
  private S3Service s3Service;

  @Autowired
  private GalleryService galleryService;

  @RequestMapping(value = "/gallery", method = RequestMethod.GET)
  public String dispWrite() {

    return "thymeleaf/gallery";
  }

  @ApiOperation(value = "이미지 추가", tags = "이미지 관리")
  @RequestMapping(value = "/gallery", method = RequestMethod.POST)
  public String execWrite(GalleryDto galleryDto, MultipartFile file) throws IOException {
    String imgPath = s3Service.upload(file);
    galleryDto.setFilePath(imgPath);

    galleryService.save(galleryDto);

    return "redirect:/gallery";
  }
}
