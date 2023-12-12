package com.plus.plus.user.service;

import com.plus.plus.user.dto.UserRequestDto;
import com.plus.plus.user.entity.User;
import com.plus.plus.user.repository.UserRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void signup(UserRequestDto requestDto) {
    String username = requestDto.getUsername();
    String password = requestDto.getPassword();
    String checkPassword = requestDto.getCheckPassword();

    // 비밀번호와 비밀번호 확인이 일치하지 않는 경우
    if (!Objects.equals(password, checkPassword)) {
      throw new IllegalArgumentException("회원가입 실패 - 비밀번호와 비밀번호 확인이 불일치 합니다.");
    }

    // 비밀번호에 닉네임과 같은 값이 포함된 경우
    if (username.contains(password)) {
      throw new IllegalArgumentException("회원가입 실패 - 비밀번호에 닉네임과 같은 값이 포함되었습니다.");
    }
    String encodePassword = passwordEncoder.encode(password);

    // 유저의 중복유무
    if (userRepository.findByUsername(username).isPresent()) {
      throw new IllegalArgumentException("회원가입 실패 - 중복된 유저네임입니다.");
    }

    User user = new User(username, encodePassword);
    userRepository.save(user);
  }
}
