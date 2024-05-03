package com.rookie.rookiee.config;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.rookie.rookiee.dto.AccountDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "B6852CCEFB5759F42CD1D8D25225D";

    public String extractUserName(String token) {
        // TODO Auto-generated method stub
        return extractClaims(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAlClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(
            Map<String, Object> extractClaims,
            AccountDto accountDto) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(accountDto.getUserName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, AccountDto accountDto) {
        final String username = extractUserName(token);
        return (username.equals(accountDto.getUserName())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        // TODO Auto-generated method stub
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        // TODO Auto-generated method stub
        return extractClaims(token, Claims::getExpiration);
    }

    private Claims extractAlClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
