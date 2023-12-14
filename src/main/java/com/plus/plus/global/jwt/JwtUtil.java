package com.plus.plus.global.jwt;

import com.plus.plus.global.exception.jwt.ExpiredJwtTokenException;
import com.plus.plus.global.exception.jwt.InvalidJwtSignatureException;
import com.plus.plus.global.exception.jwt.InvalidJwtTokenException;
import com.plus.plus.global.exception.jwt.UnsupportedJwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class JwtUtil {

  // Header KEY 값
  public static final String AUTHORIZATION_HEADER = "Authorization";

  // Token 식별자
  public static final String BEARER_PREFIX = "Bearer ";

  @Value("${jwt.secret.key}") // Base64 Encode 한 SecretKey
  private String secretKey;

  private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

  private Key key;

  @PostConstruct
  public void init() {
    byte[] bytes = Base64.getDecoder().decode(secretKey);
    key = Keys.hmacShaKeyFor(bytes);
  }

  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
      return bearerToken.substring(7);
    }
    return null;
  }

  public boolean validationToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
      return true;
    } catch (SecurityException | MalformedJwtException | SignatureException e) {
      throw new InvalidJwtSignatureException(e); // 유효하지 않는 JWT 서명 입니다.
    } catch (ExpiredJwtException e) { // 만료된 JWT token 입니다.
      throw new ExpiredJwtTokenException(e);
    } catch (UnsupportedJwtException e) { // 지원되지 않는 JWT 토큰 입니다.
      throw new UnsupportedJwtTokenException(e);
    } catch (IllegalArgumentException e) { // 잘못된 JWT 토큰 입니다.
      throw new InvalidJwtTokenException(e);
    }
  }

  public Claims getUserInfoFromToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }

  public String createToken(String username) {
    Date date = new Date();

    // 토큰 만료시간 60분
    long TOKEN_TIME = 60 * 60 * 1000;
    return BEARER_PREFIX +
        Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(date.getTime() + TOKEN_TIME))
            .setIssuedAt(date)
            .signWith(key, signatureAlgorithm)
            .compact();
  }
}
