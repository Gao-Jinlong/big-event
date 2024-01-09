package com.ginlon.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
  public static String encode(String password) {
    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      byte[] bytes = md5.digest(password.getBytes());

      StringBuilder result = new StringBuilder();
      for (byte b : bytes) {
        result.append(String.format("%02x", b));
      }
      return result.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("加密失败");
    }
  }
}
