package com.odod.domain.es;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import com.odod.dto.PositionResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Document(indexName = "position")
public class Position {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Field(type = FieldType.Text)
  private String userId;

  @Field(type = FieldType.Double)
  private Double lon;

  @Field(type = FieldType.Double)
  private Double lat;

  @Field(type = FieldType.Double)
  private Double speed;

  @Field(type = FieldType.Double)
  private Double accuracy;

  @Field(type = FieldType.Date)
  private Date logDate;

  public PositionResponseDto toResponseDto(Position position) {
    return PositionResponseDto.builder().userId(position.getUserId()).lon(position.getLon())
        .lat(position.getLat()).speed(position.getSpeed()).accuracy(position.getAccuracy()).build();
  }
}
