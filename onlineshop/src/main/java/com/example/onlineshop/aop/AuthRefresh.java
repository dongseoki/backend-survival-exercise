package com.example.onlineshop.aop;

import com.example.onlineshop.etctests.CommonTestService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Aspect
public class AuthRefresh {

  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  private final CommonTestService commonTestService;

  public AuthRefresh(CommonTestService commonTestService) {
    this.commonTestService = commonTestService;
  }

  @Around("execution(* com.example.onlineshop.etctests.TestService.*(..))")
  public Object calculatePerformanceTime(ProceedingJoinPoint proceedingJoinPoint) {
    Object result = null;
    try {
      long start = System.currentTimeMillis();
      Object[] joinPointArgs = proceedingJoinPoint.getArgs();
      Map data = (Map) joinPointArgs[0];
      commonTestService.doItSometingChange(data);

      result = proceedingJoinPoint.proceed();
      long end = System.currentTimeMillis();

      System.out.println("수행 시간 : " + (end - start));
    } catch (Throwable throwable) {
      System.out.println("exception! ");
    }
    return result;
  }
}
