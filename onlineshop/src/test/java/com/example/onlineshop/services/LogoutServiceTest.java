package com.example.onlineshop.services;

import com.example.onlineshop.daos.AuthUserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LogoutServiceTest {
  private AuthUserDao authUserDao;

  private LogoutService logoutService;

  @BeforeEach
  void setUp() {
    authUserDao = mock(AuthUserDao.class);

    logoutService = new LogoutService(authUserDao);
  }

  @Test
  void logout() {
    String accessToken = "Tester.Access.Token";

    logoutService.logout(accessToken);

    verify(authUserDao).removeAccessToken(accessToken);
  }
}
