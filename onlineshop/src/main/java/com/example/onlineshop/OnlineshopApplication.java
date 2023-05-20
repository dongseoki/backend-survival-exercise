package com.example.onlineshop;

import com.example.onlineshop.aop.AuthRefresh;
import com.example.onlineshop.etctests.CommonTestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class OnlineshopApplication {
  private final CommonTestService commonTestService;

  public OnlineshopApplication(CommonTestService commonTestService) {
    this.commonTestService = commonTestService;
  }

  public static void main(String[] args) {
    SpringApplication.run(OnlineshopApplication.class, args);
  }

  @Bean
  public AuthRefresh authRefresh() {
    return new AuthRefresh(commonTestService);
  }

}
