package com.example.securityexample.controllers;


import com.example.securityexample.SecurityExampleApplication;
import com.example.securityexample.config.AccessTokenAuthenticationFilter;
import com.example.securityexample.config.AccessTokenService;
import com.example.securityexample.config.WebSecurityConfig;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
    SecurityExampleApplication.class,
    WebSecurityConfig.class,
})
public abstract class ControllerTest {
  @SpyBean
  private AccessTokenService accessTokenService;
}