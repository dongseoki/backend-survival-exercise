package com.example.jdbctemplatepractice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class AppRunner implements CommandLineRunner {
  private final JdbcTemplate jdbcTemplate;
  private final TransactionTemplate transactionTemplate;


  public AppRunner(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.transactionTemplate = transactionTemplate;
  }

//  @Override
//  public void run(String... args) throws Exception {
//    System.out.println("-".repeat(80));
//    String sql = "SELECT name FROM company";
//
//    jdbcTemplate.query(sql, resultSet -> {
//      while (resultSet.next()) {
//        String name = resultSet.getString("name");
//        System.out.println(name);
//      }
//    });
//
//    System.out.println("-".repeat(80));
//  }

  // prepared Statment 이용.
//  @Override
//  public void run(String... args) throws Exception {
//    System.out.println("-".repeat(80));
//    String sql = "SELECT * FROM company WHERE name LIKE ?";
//
//    jdbcTemplate.query(sql, resultSet -> {
//        String name = resultSet.getString("name");
//        System.out.println(name);
//    }, "%트%");
//
//    System.out.println("-".repeat(80));
//  }

//

  @Override
  public void run(String... args) throws Exception {
    System.out.println("-".repeat(80));
    String sql = """
      INSERT INTO company(id, name, age, address, salary)
       VALUES (?, ?, ?, ?, ?)
      """;
    transactionTemplate.execute(status -> {
      jdbcTemplate.update(sql, 5, "동석컴퍼니", 29, "마포구", 10000);
      // 이런 식으로 예외를 던지면 전부 없던 일로 만들 수 있다.
//      if (true) {
//        throw new RuntimeException("FAIL!");
//      }

      return null;
    });


    System.out.println("-".repeat(80));
  }
}
