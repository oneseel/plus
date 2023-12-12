package com.plus.plus.global.common;

import lombok.Getter;

@Getter
public enum ErrorCode {

  // user
  PASSWORD_MISMATCH_EXCEPTION(401, "회원가입 실패 - 비밀번호와 비밀번호 확인이 불일치 합니다."),
  PASSWORD_CONTAINS_USERNAME_EXCEPTION(401, "회원가입 실패 - 비밀번호에 닉네임과 같은 값이 포함되었습니다."),
  ALREADY_EXIST_MEMBER_EXCEPTION(401, "회원가입 실패 - 중복된 유저네임입니다.");

  private final int status;

  private final String message;

  ErrorCode(int status, String message) {
    this.status = status;
    this.message = message;

  }
}
