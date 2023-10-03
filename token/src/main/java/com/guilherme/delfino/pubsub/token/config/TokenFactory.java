package com.guilherme.delfino.pubsub.token.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.guilherme.delfino.pubsub.token.entities.ApplicationUser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@RequiredArgsConstructor
@Slf4j
@Service
public class TokenFactory {
    private final JwtConfiguration jwtConfiguration;
    @SneakyThrows
    public String createJwt(Authentication auth){
        log.info("Creating JWT Token with user: '{}'", auth.getDetails());
        Algorithm algorithm = Algorithm.HMAC256(jwtConfiguration.getSecret());

        ApplicationUser user = (ApplicationUser) auth.getDetails();
        String token = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfiguration.getExpiration() * 1000))
                .withIssuedAt(new Date())
                .withJWTId(UUID.randomUUID().toString())
                .withClaim("userID", user.getId())
                .withSubject(auth.getPrincipal().toString())
                .withIssuer(jwtConfiguration.getIssuer())
                .withClaim("ROLES", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).map(role->"ROLE_"+role).toList())
                .sign(algorithm);

        log.info("Token has been created!");
        return token;

    }

    public DecodedJWT verifyToken(String token){
        log.info("Verifing token . . .");
        Algorithm algorithm = Algorithm.HMAC256(jwtConfiguration.getSecret());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT verifiedToken = verifier.verify(token);
        log.info("Token has been verified!");
        return verifiedToken;
    }
}
