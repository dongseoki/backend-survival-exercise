package com.example.onlineshop.etctests;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
  private final TestService testService;

  public TestController(TestService testService) {
    this.testService = testService;
  }

  @GetMapping("/{type}")
  public String test(@PathVariable int type) {
//    Map<String, String> data = Map.of("key1", "value1", "key2", "value2");
    Map<String, String> data = new HashMap<>();
    data.put("key1", "value1");
    data.put("key2", "value2");
    switch (type) {
      case 1:
        testService.createMeeting(data);
        break;
      case 2:
        testService.getMeeting(data);
        break;
      case 3:
        testService.createUser(data);
        break;
      default:
        return "Hello, this is test";
    }
    return "success";
  }
}
