package com.plus.plus.global.exception.comment;

import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.exception.common.ErrorCode;

public class CommentNotFoundException extends BusinessException {

  public CommentNotFoundException() {
    super(ErrorCode.COMMENT_NOT_FOUND_EXCEPTION);
  }
}
