package com.plus.plus.global.exception.like;

import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.exception.common.ErrorCode;

public class DuplicatedLikeException extends BusinessException {

  public DuplicatedLikeException() {
    super(ErrorCode.DUPLICATED_LIKE_EXCEPTION);
  }
}
