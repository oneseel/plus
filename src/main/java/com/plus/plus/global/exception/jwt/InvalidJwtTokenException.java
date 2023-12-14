package com.plus.plus.global.exception.jwt;

import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.exception.common.ErrorCode;

public class InvalidJwtTokenException extends BusinessException {

  public InvalidJwtTokenException(Throwable cause) {
    super(ErrorCode.INVALID_JWT_TOKEN_EXCEPTION, cause);
  }
}
