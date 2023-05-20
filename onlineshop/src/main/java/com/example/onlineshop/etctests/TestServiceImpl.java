package com.example.onlineshop.etctests;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TestServiceImpl implements TestService {
  public void createUser(Map data) {
    printData(data);
    System.out.println("create User~~!@!");
  }

  public void createMeeting(Map data) {
    printData(data);
    System.out.println("create Meeting~~!@!");
  }

  public void getMeeting(Map data) {
    printData(data);
    System.out.println("get Meeting~~!@!");
  }

  private void printData(Map data) {
    data.forEach((key, value) -> {
      System.out.println("key = " + key);
      System.out.println("value = " + value);
    });
  }
}
