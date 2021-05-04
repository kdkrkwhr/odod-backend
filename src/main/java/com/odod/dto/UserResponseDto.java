package com.odod.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Builder
@Getter
public class UserResponseDto {

  private UUID idx;
  private String userId;
  private String email;
  private String name;

  @Setter
  @Getter
  @Builder
  @ToString
  public static class ReviewUserResponseDto {
    private String userId;
  }

  @Getter
  @Builder
  public static class QuestionUserResponseDto {
    private UUID idx;
    private String userId;
  }

}
