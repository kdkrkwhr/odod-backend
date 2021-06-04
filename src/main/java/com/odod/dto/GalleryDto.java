package com.odod.dto;

import com.odod.gallery.Gallery;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryDto {

  private String email;
  private String subject;
  private String filePath;
  private Double lon;
  private Double lat;

  public Gallery toEntity() {
    Gallery build = Gallery.builder().email(email).subject(subject).filePath(filePath).lon(lon).lat(lat).build();
    return build;
  }

  @Builder
  public GalleryDto(String email, String subject, String filePath, Double lon, Double lat) {
    this.email = email;
    this.subject = subject;
    this.filePath = filePath;
    this.lon = lon;
    this.lat = lat;
  }
}
