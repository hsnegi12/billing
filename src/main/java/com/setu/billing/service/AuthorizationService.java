package com.setu.billing.service;

import java.util.Date;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class AuthorizationService {

    public static String SCHEME_ID = "8f47e41c-4684-4af1-b1b8-246c83112033";
    public static String SECRET = "7c2e036c-908f-48ba-abe3-cd8745a6fa99";
    //2 minutes authentication
    public static Long TOKEN_VALIDITY = 120000l;

    public static String yieldBearerToken() {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        String token = JWT.create()
                .withAudience(SCHEME_ID)
                .withIssuedAt(new Date())
                .withClaim("jti",  UUID.randomUUID().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .sign(algorithm);
        return "Bearer " + token;
    }

    public static void verifyBearerToken(String bearerToken) throws JWTVerificationException {
        bearerToken = bearerToken.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .acceptLeeway(10)
                .withAudience(SCHEME_ID)
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(bearerToken);
    }

}
