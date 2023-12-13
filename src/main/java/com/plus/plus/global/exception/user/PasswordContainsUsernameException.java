package com.plus.plus.global.exception.user;

import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.exception.common.ErrorCode;

public class PasswordContainsUsernameException extends BusinessException {

  public PasswordContainsUsernameException() {
    super(ErrorCode.PASSWORD_CONTAINS_USERNAME_EXCEPTION);
  }
}
