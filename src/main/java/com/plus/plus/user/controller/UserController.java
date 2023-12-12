package com.plus.plus.user.controller;

import com.plus.plus.common.CommonResponseDto;
import com.plus.plus.user.dto.UserRequestDto;
import com.plus.plus.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<CommonResponseDto> signup(@RequestBody UserRequestDto requestDto) {
      userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED.value())
            .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
  }

}
