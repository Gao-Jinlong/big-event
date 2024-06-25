package com.ginlon.interceptors;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ginlon.utils.JwtUtil;
import com.ginlon.utils.ThreadLocalUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptors implements HandlerInterceptor {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader("Authorization");

    try {
      // 从 redis 中获取 token 验证
      ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
      String redisToken = operations.get(token);

      if (redisToken == null) {
        throw new RuntimeException();
      }

      Map<String, Object> claims = JwtUtil.parseToken(token);

      // 将业务数据存储到 ThreadLocal 中
      ThreadLocalUtil.set(claims);

      return true;
    } catch (Exception e) {
      response.setStatus(401);
      return false;
    }
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    // 清空 ThreadLocal 中的数据
    ThreadLocalUtil.remove();
  }

}
