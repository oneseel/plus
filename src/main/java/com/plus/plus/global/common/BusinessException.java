package com.plus.plus.global.common;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  private final int status;

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.status = errorCode.getStatus();
  }
}
