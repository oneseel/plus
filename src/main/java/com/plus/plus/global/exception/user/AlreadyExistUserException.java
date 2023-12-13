package com.plus.plus.global.exception.user;

import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.exception.common.ErrorCode;

public class AlreadyExistUserException extends BusinessException {

  public AlreadyExistUserException() {
    super(ErrorCode.ALREADY_EXIST_MEMBER_EXCEPTION);
  }
}
