package com.ginlon;

import org.junit.jupiter.api.Test;

// ThreadLocal 用于保存线程的局部变量，是一个以 ThreadLocal 对象为键、任意对象为值的存储结构
// 这个结构被附带在线程上，也就是说一个线程可以根据一个 ThreadLocal 对象查询到绑定在这个线程上的一个值
public class ThreadLocalTest {
  @Test
  public void testThreadLocalSetAndGet() {
    ThreadLocal threadLocal = new ThreadLocal();

    new Thread(() -> {
      threadLocal.set("hello");
      System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
      System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
      System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
    }, "threadLocal hello").start();

    new Thread(() -> {
      threadLocal.set("world");
      System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
      System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
      System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
    }, "threadLocal world").start();
  }
}
