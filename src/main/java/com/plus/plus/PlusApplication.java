package com.plus.plus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication // (exclude = SecurityAutoConfiguration.class) 시큐리티 비활성
public class PlusApplication {

  public static void main(String[] args) {
    SpringApplication.run(PlusApplication.class, args);
  }

}
