package com.example.websecurity.security;

import com.example.websecurity.dao.model.Role;
import com.example.websecurity.dao.model.RoleName;
import com.example.websecurity.dao.model.User;
import com.example.websecurity.exceptions.InvalidTokenRequestException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtTokenProvider {

    private static final String TOKEN_TYPE = "JWT";

    private static final TypeReference<Map<String, Object>> REF = new TypeReference<Map<String, Object>>() {
    };

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private Long jwtExpirationInMs;

    @Value("${app.jwt.excluded.claims}")
    private Set<String> excludedClaims;

    private final ObjectMapper objectMapper;

    public String generateToken(User user) {
        var now = Instant.now();
        var expiryDate = now.plusMillis(jwtExpirationInMs);

        if (user.getRole() == null) user.setRole(Role.builder()
                .id(4L)
                .roleName(RoleName.GUEST)
                .build());

        var builder = Jwts.builder()
                .setSubject(Long.toString(user.getId()))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiryDate));

        Map<String, Object> map = objectMapper.convertValue(user, REF);
        map.keySet().removeAll(excludedClaims);
        map.forEach(builder::claim);

        return builder.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }


    public Long getUserIdFromJWT(String token) {
        var claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature", ex);
            throw new InvalidTokenRequestException(TOKEN_TYPE, token, "Incorrect signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token", ex);
            throw new InvalidTokenRequestException(TOKEN_TYPE, token, "Malformed jwt token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token", ex);
            throw new InvalidTokenRequestException(TOKEN_TYPE, token, "Token expired. Refresh required");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token", ex);
            throw new InvalidTokenRequestException(TOKEN_TYPE, token, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty", ex);
            throw new InvalidTokenRequestException(TOKEN_TYPE, token, "Illegal argument token");
        }
    }
}
