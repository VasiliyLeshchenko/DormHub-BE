package org.dormhub.www.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.dormhub.www.storage.entity.UserEntity;
import org.dormhub.www.config.properties.JwtConfigProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JoseHeaderNames;
import org.springframework.security.oauth2.jwt.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtKeyProvider jwtKeyProvider;
    private final JwtConfigProperties configProperties;

    public Jwt jwt(String token) {
        Claims claims = extractAllClaims(token);
        Date issued = claims.getIssuedAt();
        Date expiration = claims.getExpiration();
        Instant issuedInstant = Instant.ofEpochSecond(issued.getTime());
        Instant expirationInstant = Instant.ofEpochSecond(expiration.getTime());
        return Jwt.withTokenValue(token)
                .issuedAt(issuedInstant)
                .expiresAt(expirationInstant)
                .header(JoseHeaderNames.ALG, "RS256")
                .subject(claims.getSubject())
                .build();
    }

    public String generateToken(PrivateKey privateKey, UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Map<String, Object> headers = new HashMap<>();
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        if (userDetails instanceof UserEntity details) {
            claims.put("sub", details.getId());
            claims.put("roles", authorities);
            claims.put("lastName", details.getLastName());
            claims.put("firstName", details.getFirstName());
            claims.put("fatherName", details.getFatherName());
            claims.put("sex", details.getSex());
        }
        headers.put("typ", "JWT");

        return generateToken(claims, headers, privateKey);
    }

    public String generateToken(Map<String, Object> claims, Map<String, Object> headers, PrivateKey privateKey) {
        return Jwts.builder()
                .setClaims(claims)
                .setHeader(headers)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + configProperties.getExpirationTime()))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    public boolean verify(String token) {
        try {

            Jwts.parserBuilder()
                    .setSigningKey(jwtKeyProvider.getPublicKey())
                    .build()
                    .parseClaimsJws(token);

            return !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtKeyProvider.getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}