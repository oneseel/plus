package com.plus.plus.global.exception.common;

import lombok.Getter;

@Getter
public enum ErrorCode {

  // jwt
  INVALID_JWT_SIGNATURE_EXCEPTION(401, "유효하지 않는 JWT 서명 입니다."),
  EXPIRED_JWT_TOKEN_EXCEPTION(401, "만료된 JWT token 입니다."),
  UNSUPPORTED_JWT_TOKEN_EXCEPTION(401, "지원되지 않는 JWT 토큰 입니다."),
  INVALID_JWT_TOKEN_EXCEPTION(401, "잘못된 JWT 토큰 입니다."),

  // user
  PASSWORD_MISMATCH_EXCEPTION(401, "회원가입 실패 - 비밀번호와 비밀번호 확인이 불일치 합니다."),
  PASSWORD_CONTAINS_USERNAME_EXCEPTION(401, "회원가입 실패 - 비밀번호에 닉네임과 같은 값이 포함되었습니다."),
  ALREADY_EXIST_MEMBER_EXCEPTION(401, "회원가입 실패 - 중복된 유저네임입니다."),
  USER_NOT_FOUND_EXCEPTION(401, "로그인 실패 - 유저네임 또는 패스워드를 확인해주세요"),
  AUTHENTICATION_MISMATCH_EXCEPTION(401, "수정 및 삭제 권한이 없습니다."),

  // post
  POST_NOT_FOUND_EXCEPTION(401, "존재하지 않는 게시글입니다.");

  private final int status;

  private final String message;

  ErrorCode(int status, String message) {
    this.status = status;
    this.message = message;

  }
}
