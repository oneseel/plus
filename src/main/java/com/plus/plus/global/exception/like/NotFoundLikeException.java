package com.plus.plus.global.exception.like;

import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.exception.common.ErrorCode;

public class NotFoundLikeException extends BusinessException {

  public NotFoundLikeException() {
    super(ErrorCode.NOT_FOUND_LIKE_EXCEPTION);
  }
}
