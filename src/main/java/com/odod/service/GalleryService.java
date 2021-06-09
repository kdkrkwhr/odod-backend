package com.odod.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.odod.dto.GalleryDto;
import com.odod.exception.SavingsException;
import com.odod.gallery.Gallery;
import com.odod.gallery.GalleryRepository;

@Service
public class GalleryService {

  @Autowired
  private S3Service s3Service;

  @Autowired
  private GalleryRepository repository;

  @Transactional
  public void save(MultipartFile file, GalleryDto dto) {

    try {

      repository.save(Gallery.builder().email(dto.getEmail()).filePath(s3Service.upload(file)).subject(dto.getSubject())
          .lon(dto.getLon()).lat(dto.getLat()).build());

    } catch (Exception e) {
      throw new SavingsException("gallery data insert fail");
    }
  }
}
