package com.plus.plus.user.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRequestDto {

  @Pattern(regexp = "^[a-zA-Z0-9]{3,10}$")
  private String username;

  @Pattern(regexp = "^[a-zA-Z0-9]{4,}$")
  private String password;

  @Pattern(regexp = "^[a-zA-Z0-9]{4,}$")
  private String checkPassword;
}
