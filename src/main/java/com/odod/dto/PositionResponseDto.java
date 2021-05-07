package com.odod.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;
import com.odod.dto.UserResponseDto.UserResponseDtoBuilder;

@Builder
@Getter
public class PositionResponseDto {

  private String userId;
  private Double lon;
  private Double lat;
}
