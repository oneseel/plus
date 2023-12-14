package com.plus.plus.global.exception.post;

import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.exception.common.ErrorCode;

public class PostNotFoundException extends BusinessException {

  public PostNotFoundException() {
    super(ErrorCode.POST_NOT_FOUND_EXCEPTION);
  }
}
