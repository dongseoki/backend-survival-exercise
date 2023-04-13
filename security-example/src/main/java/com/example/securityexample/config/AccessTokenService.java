package com.example.securityexample.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessTokenService {
  public Authentication authenticate(String accessToken) {
    if (!accessToken.equals("TOKEN")) {
      return null;
    }

    Authentication authentication =
        UsernamePasswordAuthenticationToken.authenticated(
            "User", null, List.of());
    return authentication;
  }
}