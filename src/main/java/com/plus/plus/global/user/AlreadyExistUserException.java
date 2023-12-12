package com.plus.plus.global.user;

import com.plus.plus.global.common.BusinessException;
import com.plus.plus.global.common.ErrorCode;

public class AlreadyExistUserException extends BusinessException {

  public AlreadyExistUserException() {
    super(ErrorCode.ALREADY_EXIST_MEMBER_EXCEPTION);
  }
}
