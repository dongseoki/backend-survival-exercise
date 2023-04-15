package com.example.securityexample.controllers;


import com.example.securityexample.SecurityExampleApplication;
import com.example.securityexample.config.AccessTokenService;
import com.example.securityexample.config.WebSecurityConfig;
import com.example.securityexample.daos.UserDetailsDao;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ContextConfiguration(classes = {
    SecurityExampleApplication.class,
    WebSecurityConfig.class,
})
public abstract class ControllerTest {
  @MockBean
  protected UserDetailsDao userDetailsDao; // ← 여기로 가져온다.
  @SpyBean
  private AccessTokenService accessTokenService;

  @BeforeEach
  void setUpUserDetailsDaoForAuthentication() { // ← 이 이름을 똑같이 쓰면 override된다는 점에 주의!
    UserDetails userDetails = User.withUsername("UserID")
                                  .password("TOKEN")
                                  .authorities("ROLE_USER")
                                  .build();

    given(userDetailsDao.findByAccessToken("TOKEN"))
        .willReturn(Optional.of(userDetails));
  }
}