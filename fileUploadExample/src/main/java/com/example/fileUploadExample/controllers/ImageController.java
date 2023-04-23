package com.example.fileUploadExample.controllers;

import com.example.fileUploadExample.dtos.UploadImageDto;
import com.example.fileUploadExample.services.ImageStorage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@CrossOrigin
public class ImageController {
  private final ImageStorage imageStorage;

  public ImageController(ImageStorage imageStorage) {
    this.imageStorage = imageStorage;
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public String create(
      @ModelAttribute UploadImageDto imageDto
  ) throws IOException {
    MultipartFile multipartFile = imageDto.image();

    if (multipartFile == null || multipartFile.isEmpty()) {
      return "No image";
    }

    return imageStorage.save(multipartFile.getBytes());
  }
}