package com.odod.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameFromTokenException extends RuntimeException {

  public UsernameFromTokenException(String msg) {
    super("UsernameFromTokenException, ".concat(msg));
  }
}
