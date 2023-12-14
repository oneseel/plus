package com.plus.plus.user.service;

import com.plus.plus.global.exception.user.AlreadyExistUserException;
import com.plus.plus.global.exception.user.PasswordContainsUsernameException;
import com.plus.plus.global.exception.user.PasswordMismatchException;
import com.plus.plus.global.exception.user.UserNotFoundException;
import com.plus.plus.user.dto.UserLoginRequestDto;
import com.plus.plus.user.dto.UserSignupRequestDto;
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
  public void signup(UserSignupRequestDto requestDto) {
    String username = requestDto.getUsername();
    String password = requestDto.getPassword();
    String checkPassword = requestDto.getCheckPassword();

    // 비밀번호와 비밀번호 확인이 일치하지 않는 경우
    if (!Objects.equals(password, checkPassword)) {
      throw new PasswordMismatchException();
    }

    // 비밀번호에 닉네임과 같은 값이 포함된 경우
    if (username.contains(password)) {
      throw new PasswordContainsUsernameException();
    }
    String encodePassword = passwordEncoder.encode(password);

    // 유저의 중복유무
    if (userRepository.findByUsername(username).isPresent()) {
      throw new AlreadyExistUserException();
    }

    User user = new User(username, encodePassword);
    userRepository.save(user);
  }

  public void login(UserLoginRequestDto requestDto) {
    String username = requestDto.getUsername();
    String password = requestDto.getPassword();

    // 저장된 회원이 없는 경우
    User user = userRepository.findByUsername(username)
        .orElseThrow(UserNotFoundException::new);

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new UserNotFoundException();
    }
  }


}
