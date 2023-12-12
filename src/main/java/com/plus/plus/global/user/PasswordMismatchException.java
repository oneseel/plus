package com.plus.plus.global.user;

import com.plus.plus.global.common.BusinessException;
import com.plus.plus.global.common.ErrorCode;

public class PasswordMismatchException extends BusinessException {

  public PasswordMismatchException() {
    super(ErrorCode.PASSWORD_MISMATCH_EXCEPTION);
  }
}
