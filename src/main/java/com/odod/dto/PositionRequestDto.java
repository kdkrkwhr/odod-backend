package com.odod.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PositionRequestDto {

  @NotBlank(message = "사용자 식별 값 을 입력하세요.")
  private String userId;

  @NotBlank(message = "경도 값 을 입력하세요.")
  private Double lon;

  @NotBlank(message = "위도 값 을 입력하세요.")
  private Double lat;

  @NotBlank(message = "현재 속도 값 을 입력하세요.")
  private Double speed;

  @NotBlank(message = "위치 정확도 값 을 입력하세요.")
  private Double accuracy;

  @NotBlank(message = "데이터 시각을 입력하세요.")
  private LocalDateTime gpsDatetime;
}
