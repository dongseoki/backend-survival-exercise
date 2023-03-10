/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.dongseoki.http.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

public class App {

  public static void main(String[] args) throws IOException {
    App app = new App();
    app.run();
  }

  private void run() throws IOException {
    // 1 서버 객체 준비
    InetSocketAddress address = new InetSocketAddress(8080);
    int backlog = 0;

    HttpServer httpServer = HttpServer.create(address, backlog);

    // 2 url 핸들러 지정.
    httpServer.createContext("/", (exchange) -> {
      // 요청 출력하자~
      displayReqeust(exchange);

      // 응답 반환도 해보자~
      String text = "hello world";
      returnRequest(exchange, text);
    });

    httpServer.createContext("/ds", (exchange) -> {
      // 요청 출력하자~
      displayReqeust(exchange);

      // 응답 반환도 해보자~
      String text = "hi dongseoki hello world";
      returnRequest(exchange, text);
    });

    // 3 listen
    httpServer.start();

  }


  private void displayReqeust(HttpExchange exchange) throws IOException {
    String method = exchange.getRequestMethod();
    System.out.println(method);

    URI uri = exchange.getRequestURI();
    String path = uri.getPath();
    System.out.println(path);

    Headers headers = exchange.getRequestHeaders();
    for (String key : headers.keySet()) {
      System.out.println(key + ": " + headers.get(key));
    }

    InputStream inputStream = exchange.getRequestBody();
    String body = new String(inputStream.readAllBytes());
    System.out.println(body);
  }


  private void returnRequest(HttpExchange exchange, String body) throws IOException {
    byte[] bytes = body.getBytes();
    exchange.sendResponseHeaders(200, bytes.length);
    OutputStream outputStream = exchange.getResponseBody();
    outputStream.write(bytes);
    outputStream.flush();
  }
}
