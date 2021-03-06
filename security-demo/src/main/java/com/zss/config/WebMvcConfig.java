package com.zss.config;

import java.util.List;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    // 注册Spring data jpa pageable的参数分解器
//    argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
  }
}
