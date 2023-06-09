package com.example.OnlineStore.JWTToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JsonWebTokenUtil {
        private static String SECRET = "SECRET";
        private static final long VALIDITY = 10 * 3600;

        public static String generateToken(String userName) {
            return Jwts.builder()
                    .setSubject(userName)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + VALIDITY*1000))
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    .compact();
        }

        public static String validateToken(String token) {
            Claims claims = verifyToken(token);
            if(isTokenExpired(claims)) {
                throw new MalformedJwtException("JWT expired");
            }
            return claims.getSubject();
        }

        private static boolean isTokenExpired(Claims claims) {
            return claims.getExpiration().before(new Date());
        }

        private static Claims verifyToken(String token) {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }
    }