package com.plus.plus.global.exception.user;

import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.exception.common.ErrorCode;

public class UserNotFoundException extends BusinessException {

  public UserNotFoundException() {
    super(ErrorCode.USER_NOT_FOUND_EXCEPTION);
  }
}
