package com.odod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.odod.dto.GalleryDto;
import com.odod.gallery.GalleryRepository;

@Service
public class GalleryService {

  @Autowired
  private GalleryRepository repository;

  public void save(GalleryDto galleryDto) {
    repository.save(galleryDto.toEntity());
  }
}
