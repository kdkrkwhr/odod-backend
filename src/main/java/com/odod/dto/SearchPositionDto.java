package com.odod.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SearchPositionDto {

  @NotBlank(message = "사용자 이메일을 알려주세요.")
  private String email;

  @NotBlank(message = "검색 시작일자를 알려주세요.")
  private String from;

  @NotBlank(message = "검색 종료일자를 알려주세요.")
  private String to;
}
