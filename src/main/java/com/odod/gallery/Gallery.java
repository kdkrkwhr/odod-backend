package com.odod.gallery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.odod.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Gallery extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String subject;

  @Column(nullable = false)
  private String filePath;

  @Column
  private Double lon;

  @Column
  private Double lat;

  @Builder
  public Gallery(String email, String subject, String content, String filePath, Double lon, Double lat) {
    this.email = email;
    this.subject = subject;
    this.filePath = filePath;
    this.lon = lon;
    this.lat = lat;
  }
}