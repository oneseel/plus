package com.plus.plus.global.user;

import com.plus.plus.global.common.BusinessException;
import com.plus.plus.global.common.ErrorCode;

public class PasswordContainsUsernameException extends BusinessException {

  public PasswordContainsUsernameException() {
    super(ErrorCode.PASSWORD_CONTAINS_USERNAME_EXCEPTION);
  }
}
