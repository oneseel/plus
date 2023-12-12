package com.plus.plus.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonResponseDto {
  private String msg;
  private Integer statusCode;

}
