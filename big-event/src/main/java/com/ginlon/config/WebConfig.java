package com.ginlon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ginlon.interceptors.LoginInterceptors;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Autowired
  private LoginInterceptors loginInterceptors;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(loginInterceptors).excludePathPatterns("/user/login", "/user/register");

    WebMvcConfigurer.super.addInterceptors(registry);
  }
}
