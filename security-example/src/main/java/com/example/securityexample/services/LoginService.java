package com.example.securityexample.services;

import com.example.securityexample.config.AccessTokenGenerator;
import com.example.securityexample.daos.UserDetailsDao;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LoginService {
  private final AccessTokenGenerator accessTokenGenerator;

  private final UserDetailsDao userDetailsDao;

  public LoginService(AccessTokenGenerator accessTokenGenerator,
                      UserDetailsDao userDetailsDao) {
    this.accessTokenGenerator = accessTokenGenerator;
    this.userDetailsDao = userDetailsDao;
  }

  public String login(String username, String password) {
    return userDetailsDao.findByUsername(username)
                         .filter(userDetails -> password.equals("password"))
                         .map(userDetails -> {
                           String id = userDetails.getUsername();
                           String accessToken = accessTokenGenerator.generate(id);
                           userDetailsDao.addAccessToken(id, accessToken);
                           return accessToken;
                         })
                         .orElseThrow(() -> new BadCredentialsException("Login failed"));
  }
}