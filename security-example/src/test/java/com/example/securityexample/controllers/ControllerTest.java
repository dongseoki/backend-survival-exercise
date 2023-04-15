package com.example.securityexample.controllers;


import com.example.securityexample.SecurityExampleApplication;
import com.example.securityexample.config.AccessTokenGenerator;
import com.example.securityexample.config.AccessTokenService;
import com.example.securityexample.config.WebSecurityConfig;
import com.example.securityexample.daos.UserDetailsDao;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ContextConfiguration(classes = {
    SecurityExampleApplication.class,
    WebSecurityConfig.class,
})
@ActiveProfiles("test")
public abstract class ControllerTest {
  protected static final String USER_ID = "UserId";
  protected static final String ADMIN_ID = "AdminId";
  @MockBean
  protected UserDetailsDao userDetailsDao; // ← 여기로 가져온다.
  protected String userAccessToken;

  protected String adminAccessToken;
  @SpyBean
  protected AccessTokenGenerator accessTokenGenerator;
  @SpyBean
  private AccessTokenService accessTokenService;

  @BeforeEach
  void setUpAccessTokenAndUserDetailsDaoForAuthentication() {
    userAccessToken = accessTokenGenerator.generate(USER_ID);
    adminAccessToken = accessTokenGenerator.generate(ADMIN_ID);

    UserDetails user = User.withUsername(USER_ID)
                           .password(userAccessToken)
                           .authorities("ROLE_USER")
                           .build();

    given(userDetailsDao.findByAccessToken(userAccessToken))
        .willReturn(Optional.of(user));

    UserDetails admin = User.withUsername(ADMIN_ID)
                            .password(adminAccessToken)
                            .authorities("ROLE_ADMIN")
                            .build();

    given(userDetailsDao.findByAccessToken(adminAccessToken))
        .willReturn(Optional.of(admin));
  }
}