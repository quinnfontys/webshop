package com.qrdsn.fullstackbackend.service;

import com.qrdsn.fullstackbackend.model.JWSData;
import com.qrdsn.fullstackbackend.model.dto.JWSDTO;
import com.qrdsn.fullstackbackend.model.dto.UserDTO;
import com.sun.istack.NotNull;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;

@Service
public class JWTService {
    Calendar calendar;
    SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final String ISSUER = "webshop";

    public UserDTO generateJWS(@NotNull UserDTO userDTO) {
        Claims claims = Jwts.claims();

        claims.setIssuer(ISSUER);
        claims.setSubject(String.valueOf(userDTO.getId()));
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 7);
        Date date = calendar.getTime();
        claims.setExpiration(date);
        userDTO.setJwsString(Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setClaims(claims)
                .claim("id", userDTO.getId())
                .claim("email", userDTO.getEmail())
                .signWith(secretKey)
                .compact());
        return userDTO;
    }

    public JWSData verifyJWS(JWSDTO jwsDTO) throws JwtException {
        Jws<Claims> claims;
        claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwsDTO.getJWSString());

        JWSData jwsData = new JWSData();
        jwsData.setExpiration(claims.getBody().getExpiration());
        jwsData.setEmail(claims.getBody().get("email", String.class));
        jwsData.setIssuer(claims.getBody().getIssuer());
        jwsData.setId(claims.getBody().getSubject());

        return jwsData;
    }
}
