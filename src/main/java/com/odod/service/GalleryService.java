package com.odod.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.odod.dto.GalleryDto;
import com.odod.exception.SavingsException;
import com.odod.gallery.Gallery;
import com.odod.gallery.GalleryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class GalleryService {

  static final Logger logger = LoggerFactory.getLogger(GalleryService.class);

  private JPAQueryFactory factory;

  @Autowired
  private S3Service s3Service;

  @Autowired
  private GalleryRepository repository;

  @Transactional
  public String save(MultipartFile file, GalleryDto dto) {
    String filePath;

    try {

      filePath = s3Service.upload(file);

      repository.save(Gallery.builder().email(dto.getEmail()).filePath(filePath).subject(dto.getSubject())
          .lon(dto.getLon()).lat(dto.getLat()).build());

      return filePath;

    } catch (Exception e) {
      throw new SavingsException("gallery data insert fail");
    }
  }

  public List<Gallery> selectGalleryData() {
    List<Gallery> resultDatas = new ArrayList<Gallery>();

    resultDatas = repository.findAll();

    return resultDatas;
  }
}
