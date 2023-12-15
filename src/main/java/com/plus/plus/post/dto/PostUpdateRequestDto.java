package com.plus.plus.post.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostUpdateRequestDto {

  @NotBlank
  @Lob
  @Size(max = 500)
  private String title;

  @NotBlank
  @Lob
  @Size(max = 5000)
  private String content;
}
