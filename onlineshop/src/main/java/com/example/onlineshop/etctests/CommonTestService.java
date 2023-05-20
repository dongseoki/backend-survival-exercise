package com.example.onlineshop.etctests;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CommonTestService {
  public void doItSometingChange(Map map) {
    if (map.containsKey("key1")) {
      System.out.println("key1 is exist");
      map.replace("key1", "this is new Value");
    } else {
      System.out.println("key1 is not exist");
    }
  }
}
