package com.odod.dto;

import javax.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PositionRequestDto {

  @NotBlank(message = "사용자 아이디를 알려주세요.")
  private String userId;

  @NotBlank(message = "경도 값 을 입력하세요.")
  private Double lon;

  @NotBlank(message = "위도 값 을 입력하세요.")
  private Double lat;
}
