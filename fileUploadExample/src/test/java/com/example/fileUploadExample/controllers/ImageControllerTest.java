package com.example.fileUploadExample.controllers;

import com.example.fileUploadExample.services.ImageStorage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImageController.class)
class ImageControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private ImageStorage imageStorage;

  @Test
  @DisplayName("POST /images")
  void create() throws Exception {
    String filename = "src/test/resources/files/test.jpg";

    MockMultipartFile file = new MockMultipartFile(
        "image", "test.jpg", "image/jpeg",
        new FileInputStream(filename));

    mockMvc.perform(multipart("/images")
               .file(file))
           .andExpect(status().isCreated());

    // TODO: 지금은 컨트롤러에서 모두 처리하기 때문에 테스트하기 어렵지만,
    //       다른 객체에 파일 저장 기능을 위임하면 verify 테스트를 써주자.
  }
}