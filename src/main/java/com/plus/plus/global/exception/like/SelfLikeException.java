package com.plus.plus.global.exception.like;

import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.exception.common.ErrorCode;

public class SelfLikeException extends BusinessException {

  public SelfLikeException() {
    super(ErrorCode.SELF_LIKE_EXCEPTION);
  }
}
