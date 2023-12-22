package com.plus.plus.user.controller;

import com.plus.plus.common.CommonResponseDto;
import com.plus.plus.global.exception.common.BusinessException;
import com.plus.plus.global.jwt.JwtUtil;
import com.plus.plus.user.dto.UserLoginRequestDto;
import com.plus.plus.user.dto.UserSignupRequestDto;
import com.plus.plus.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  private final JwtUtil jwtUtil;

  // 회원가입
  @PostMapping("/signup")
  public ResponseEntity<?> signup(@Valid @RequestBody UserSignupRequestDto requestDto) {
    try {
      userService.signup(requestDto);
      return ResponseEntity.status(HttpStatus.CREATED.value())
          .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    } catch (BusinessException be) {
      return ResponseEntity.status(be.getStatus())
          .body(new CommonResponseDto(be.getMessage(), be.getStatus()));
    }
  }

  // 유저네임 중복환인
  @GetMapping("/signup")
  public ResponseEntity<?> checkUsername(
      @RequestBody UserSignupRequestDto requestDto) {
    try {
      userService.checkUsername(requestDto);
      return ResponseEntity.ok()
          .body(new CommonResponseDto("중복된 유저네임이 아닙니다.", HttpStatus.OK.value()));
    } catch (BusinessException be) {
      return ResponseEntity.status(be.getStatus())
          .body(new CommonResponseDto(be.getMessage(), be.getStatus()));
    }
  }

  // 로그인
  @PostMapping("/login")
  public ResponseEntity<?> login(
      @RequestBody UserLoginRequestDto requestDto, HttpServletResponse response) {
    try {
      userService.login(requestDto);
    } catch (BusinessException be) {
      return ResponseEntity.status(be.getStatus())
          .body(new CommonResponseDto(be.getMessage(), be.getStatus()));
    }

    // 로그인 시 헤더에 JWT 토큰이 보임
    response.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(requestDto.getUsername()));

    return ResponseEntity.ok()
        .body(new CommonResponseDto("로그인 성공", HttpStatus.OK.value()));
  }
}
