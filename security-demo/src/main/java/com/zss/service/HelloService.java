package com.zss.service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HelloService {

  public String greeting(String name) {
    log.info("-------------Greeting!");
    return "Hello " + name;
  }
}
