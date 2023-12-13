package com.plus.plus.global.exception.user;

import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.exception.common.ErrorCode;

public class PasswordMismatchException extends BusinessException {

  public PasswordMismatchException() {
    super(ErrorCode.PASSWORD_MISMATCH_EXCEPTION);
  }
}
