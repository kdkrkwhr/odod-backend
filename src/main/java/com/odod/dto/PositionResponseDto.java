package com.odod.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.Date;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Builder
@Document(indexName = "position")
public class PositionResponseDto {

  private String userId;
  private Double lon;
  private Double lat;
  private Double speed;
  private Double accuracy;
  private Date logDate;
}
