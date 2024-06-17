package com.ginlon;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;

public class JwtTest {
  @Test
  public void testGen() {
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", "1");
    claims.put("username", "张三");

    String token = JWT.create().withClaim("user", claims)
        .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
        .sign(Algorithm.HMAC256("ginlon"));

    System.out.println(token);
  }

  @Test
  public void testParse() {
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiMSIsInVzZXJuYW1lIjoi5byg5LiJIn0sImV4cCI6MTcwNTM3NDIyOH0.V6EKbfgGBW333hW4_JZtSpt1NInqmBx0-ZedBbpIPms";
    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("ginlon")).build();

    DecodedJWT decodedJWT = jwtVerifier.verify(token);

    Map<String, Claim> claims = decodedJWT.getClaims();

    System.out.println(claims.get("user"));
  }
}
