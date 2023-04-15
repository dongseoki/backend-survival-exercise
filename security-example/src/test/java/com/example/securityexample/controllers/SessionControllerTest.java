package com.example.securityexample.controllers;

import com.example.securityexample.config.AccessTokenGenerator;
import com.example.securityexample.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
class SessionControllerTest extends ControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private LoginService loginService;

  @SpyBean
  private AccessTokenGenerator accessTokenGenerator;

  @BeforeEach
  void setUp() {
    UserDetails userDetails = User.withUsername("UserID")
                                  .password("password")
                                  .authorities("ROLE_USER")
                                  .build();

    given(userDetailsDao.findByUsername("tester"))
        .willReturn(Optional.of(userDetails));
  }

  @Test
  @DisplayName("POST /session - with correct username and password")
  void loginSuccess() throws Exception {
    String json = """
        {
            "username": "tester",
            "password": "password"
        }
        """;

    mockMvc.perform(post("/session")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json))
           .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("POST /session - with incorrect username")
  void loginWithIncorrectUsername() throws Exception {
    String json = """
        {
            "username": "xxx",
            "password": "password"
        }
        """;

    mockMvc.perform(post("/session")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json))
           .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("POST /session - with incorrect password")
  void loginWithIncorrectPassword() throws Exception {
    String json = """
        {
            "username": "tester",
            "password": "xxx"
        }
        """;

    mockMvc.perform(post("/session")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json))
           .andExpect(status().isBadRequest());
  }
}