package com.plus.plus.global.exception.user;

import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.exception.common.ErrorCode;

public class AuthenticationMismatchException extends BusinessException {

  public AuthenticationMismatchException() {
    super(ErrorCode.AUTHENTICATION_MISMATCH_EXCEPTION);
  }
}
