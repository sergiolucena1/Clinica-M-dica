package com.br.clinica.clinica_v1.authentication.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.br.clinica.clinica_v1.authentication.model.UserModel;
import com.br.clinica.clinica_v1.dto.TokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h

    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDto gerarToken(UserModel usuario) {

        List<String> roles = usuario.getRoles().stream()
                .map( u -> u.getRoleName().toString()).collect(Collectors.toList());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        var accessToken = getAccessToken(usuario.getUsername(), roles, now, validity);
        var refreshToken = getRefreshToken(usuario.getUsername(), roles, now);

        return new TokenDto(usuario.getUsername(), true, now, validity, accessToken, refreshToken);
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
        String issuerUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath().build().toUriString()
                + "/ClinicaV1";
        return JWT.create()
                .withClaim("listaPerfeil", (Date) roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(username)
                .withIssuer(issuerUrl)
                .sign(algorithm)
                .trim();
    }

    private String getRefreshToken(String username, List<String> roles, Date now) {
        Date validityRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3));
        return JWT.create()
                .withClaim("roles", (Date) roles)
                .withIssuedAt(now)
                .withExpiresAt(validityRefreshToken)
                .withSubject(username)
                .sign(algorithm)
                .trim();
    }

    public String getSubject(String token) {
       return JWT.require(Algorithm.HMAC256(secretKey.getBytes()))
                .build().verify(token).getSubject();
    }

//    public TokenDto refreshToken(String refreshToken) {
//        if (refreshToken.contains("Bearer ")) refreshToken =
//                refreshToken.substring("Bearer ".length());
//
//        JWTVerifier verifier = JWT.require(algorithm).build();
//        DecodedJWT decodedJWT = verifier.verify(refreshToken);
//        String username = decodedJWT.getSubject();
//        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
//        return gerarToken(username, roles);
//    }

}
