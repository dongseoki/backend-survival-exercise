package com.example.fileUploadExample.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.hypersistence.tsid.TSID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Component
public class ImageStorage {
  private final Cloudinary cloudinary;
  public ImageStorage(
      @Value("${cloudinary.url}") String cloudinaryUrl
  ) {
    cloudinary = new Cloudinary(cloudinaryUrl);
    cloudinary.config.secure = true;
  }

  public String save(byte[] bytes) {
    String id = TSID.Factory.getTsid().toString();

    Map options = ObjectUtils.asMap(
        "public_id", "test/" + id
    );

    try {
      Map result = cloudinary.uploader().upload(bytes, options);
      return result.get("secure_url").toString();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void saveInLocalServer(byte[] bytes) {
    String id = TSID.Factory.getTsid().toString();

    File file = new File("data/" + id + ".jpg");

    try (OutputStream outputStream = new FileOutputStream(file)) {
      outputStream.write(bytes);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}