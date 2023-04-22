package com.example.fileUploadExample.dtos;

import org.springframework.web.multipart.MultipartFile;

public record UploadImageDto(MultipartFile image) {
}
