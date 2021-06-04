package com.odod.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import com.odod.dto.GalleryDto;
import com.odod.service.GalleryService;
import com.odod.service.S3Service;
import java.io.IOException;

@Controller
@AllArgsConstructor
public class GalleryController {

  @Autowired
  private S3Service s3Service;

  @Autowired
  private GalleryService galleryService;

  @GetMapping("/gallery")
  public String dispWrite() {

    return "/gallery";
  }

  @PostMapping("/gallery")
  public String execWrite(GalleryDto galleryDto, MultipartFile file) throws IOException {
    String imgPath = s3Service.upload(file);
    galleryDto.setFilePath(imgPath);

    galleryService.save(galleryDto);

    return "redirect:/gallery";
  }
}
