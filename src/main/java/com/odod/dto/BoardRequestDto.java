package com.odod.dto;

import javax.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardRequestDto {

  @NotBlank(message = "사용자 이메일을 입력하세요.")
  private String email;

  @NotBlank(message = "제목을 입력하세요.")
  private String subject;

  @NotBlank(message = "내용을 입력하세요.")
  private String content;

  @NotBlank(message = "파일 경로를 입력하세요.")
  private String filePath;

  @NotBlank(message = "경도 값 을 입력하세요.")
  private Double lon;
  
  @NotBlank(message = "위도 값 을 입력하세요.")
  private Double lat;
}
