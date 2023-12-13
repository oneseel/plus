package com.plus.plus.global.exception.jwt;

import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.exception.common.ErrorCode;

public class ExpiredJwtTokenException extends BusinessException {

  public ExpiredJwtTokenException(Throwable cause) {
    super(ErrorCode.EXPIRED_JWT_TOKEN_EXCEPTION, cause);
  }
}
