package com.example.onlineshop.controllers;

import com.example.onlineshop.OnlineshopApplication;
import com.example.onlineshop.daos.AuthUserDao;
import com.example.onlineshop.models.AuthUser;
import com.example.onlineshop.security.AccessTokenGenerator;
import com.example.onlineshop.security.AccessTokenService;
import com.example.onlineshop.security.WebSecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ContextConfiguration(classes = {
    OnlineshopApplication.class,
    WebSecurityConfig.class,
})
public abstract class ControllerTest {
  protected static final String USER_ID = "UserId";
  protected static final String ADMIN_ID = "AdminId";
  @SpyBean
  protected AccessTokenGenerator accessTokenGenerator;
  @MockBean
  protected AuthUserDao authUserDao;
  protected String userAccessToken;
  protected String adminAccessToken;
  @SpyBean
  private AccessTokenService accessTokenService;

  @BeforeEach
  void setUpAccessTokenAndUserDetailsDaoForAuthentication() {
    userAccessToken = accessTokenGenerator.generate(USER_ID);
    adminAccessToken = accessTokenGenerator.generate(ADMIN_ID);

    AuthUser user = AuthUser.authenticated(
        USER_ID, "ROLE_USER", userAccessToken);

    given(authUserDao.findByAccessToken(userAccessToken))
        .willReturn(Optional.of(user));

    AuthUser admin = AuthUser.authenticated(
        ADMIN_ID, "ROLE_ADMIN", adminAccessToken);

    given(authUserDao.findByAccessToken(adminAccessToken))
        .willReturn(Optional.of(admin));
  }
}
