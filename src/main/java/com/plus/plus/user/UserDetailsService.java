package com.plus.plus.user;

import com.plus.plus.global.exception.user.UserNotFoundException;
import com.plus.plus.user.entity.User;
import com.plus.plus.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService {

  private final UserRepository userRepository;

  public UserDetails getUserDetails(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(UserNotFoundException::new);
    return new UserDetailsImpl(user);
  }
}
