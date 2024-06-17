package com.ginlon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest // 这个注解是SpringBoot提供的，用于测试类，表示这是一个SpringBoot的测试类，可以在测试类中注入Spring容器
public class RedisTest {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Test
  public void testSet() {
    ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
    operations.set("name", "ginlon");
    operations.set("id", "1", 15, TimeUnit.SECONDS); // 设置过期时间
  }

  @Test
  public void testGet() {
    ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
    String name = operations.get("name");
    assertEquals("ginlon", name);
  }

  @Test
  public void testDel() {
    stringRedisTemplate.delete("name");
  }
}
