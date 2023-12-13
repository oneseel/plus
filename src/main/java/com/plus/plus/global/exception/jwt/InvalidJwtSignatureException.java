package com.plus.plus.global.exception.jwt;

import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.exception.common.ErrorCode;

public class InvalidJwtSignatureException extends BusinessException {

  public InvalidJwtSignatureException(Throwable cause) {
    super(ErrorCode.EXPIRED_JWT_TOKEN_EXCEPTION, cause);
  }
}
