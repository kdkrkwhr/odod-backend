package com.odod.exception;

import org.springframework.http.HttpStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ExceptionModel {

  private long reusultCode;
  private String errorMessage;
  private HttpStatus statusCode;

}
