package com.example.fileUploadExample.controllers;

import com.example.fileUploadExample.dtos.UploadImageDto;
import io.hypersistence.tsid.TSID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/images")
public class ImageController {
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public String create(
      @ModelAttribute UploadImageDto imageDto
  ) throws IOException {
    MultipartFile multipartFile = imageDto.image();

    if (multipartFile == null || multipartFile.isEmpty()) {
      return "No image";
    }

    String id = TSID.Factory.getTsid().toString();
    File file = new File("data/" + id + ".jpg");

    try (OutputStream outputStream = new FileOutputStream(file)) {
      byte[] content = multipartFile.getBytes();
      outputStream.write(content);
    }

    return multipartFile.getOriginalFilename();
  }
}