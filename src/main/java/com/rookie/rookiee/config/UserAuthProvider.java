package com.rookie.rookiee.config;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.rookie.rookiee.entity.Eusers;

import com.rookie.rookiee.service.EusersService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserAuthProvider {

    private final EusersService eusersService;

    @Value("${security.jwt.token.secret-key}")
    private String secretkey;

    @PostConstruct
    protected void init() {
        secretkey = Base64.getEncoder().encodeToString(secretkey.getBytes());
    }

    public String createToken(String email) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 1000 * 60 * 60 * 24);

        return JWT.create()
                .withIssuer(email)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC256(secretkey));
    }

    public Authentication validateToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretkey)).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        Eusers eusers = eusersService.loadEusersByEmail(decodedJWT.getIssuer());

        return new UsernamePasswordAuthenticationToken(eusers, null, eusers.getAuthorities());
    }

}
