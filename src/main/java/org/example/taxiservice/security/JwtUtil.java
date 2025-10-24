package org.example.taxiservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    // Секретный ключ (должен быть длинным и сложным)
    private final String secret = "MySuperSecretKeyForJWTGeneration12345";

    // Время жизни токена (1 час)
    private final long expirationMillis = 3600_000;

    // Генерация токена
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    // Извлечение username
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Извлечение роли
    public String extractRole(String token) {
        return (String) getClaims(token).get("role");
    }

    // Проверка токена
    public boolean validateToken(String token, String username) {
        String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    // Проверка срока действия токена
    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // Метод для фильтра: возвращает claims
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    // Альтернатива для фильтра
    public Claims extractClaims(String token) {
        return getClaims(token);
    }
}
