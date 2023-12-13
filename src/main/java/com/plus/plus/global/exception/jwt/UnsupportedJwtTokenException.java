package com.plus.plus.global.exception.jwt;

import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.exception.common.ErrorCode;

public class UnsupportedJwtTokenException extends BusinessException {

  public UnsupportedJwtTokenException(Throwable cause) {
    super(ErrorCode.UNSUPPORTED_JWT_TOKEN_EXCEPTION, cause);
  }
}
