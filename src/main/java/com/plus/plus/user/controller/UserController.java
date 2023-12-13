package com.plus.plus.user.controller;

import com.plus.plus.common.CommonResponseDto;
import com.plus.plus.global.common.BusinessException;
import com.plus.plus.global.common.ErrorResponse;
import com.plus.plus.user.dto.UserRequestDto;
import com.plus.plus.user.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  // 회원가입
  @PostMapping("/signup")
  public ResponseEntity<?> signup(@Valid @RequestBody UserRequestDto requestDto) {
    try {
      userService.signup(requestDto);
      return ResponseEntity.status(HttpStatus.CREATED.value())
          .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    } catch (BusinessException be) {
      return ResponseEntity.status(be.getStatus())
          .body(new CommonResponseDto(be.getMessage(), be.getStatus()));
    }
  }

}
