package com.sanger.springular.jwt;

import static java.util.Date.from;

import java.nio.charset.Charset;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

import com.sanger.springular.model.UserEntity;
import com.sanger.springular.model.UserRole;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.java.Log;

@Component
@Log
public class JwtProvider {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    @Value("${jwt.secret:EnUnLugarDeLaManchaDeCuyoNombreNoQuieroAcordarmeNoHaMuchoTiempoQueViviaUnHidalgo}")
    private String jwtSecreto;

    @Value("${jwt.token-expiration:86400}")
    private Long jwtDurationToken;

    public String generateToken(Authentication auth) {

        UserEntity user = (UserEntity) auth.getPrincipal();

        Date tokenExpirationDate = new Date(System.currentTimeMillis() + (this.jwtDurationToken * 1000));

        return Jwts.builder().signWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()), SignatureAlgorithm.HS256)
                .setHeaderParam("type", TOKEN_TYPE).setSubject(Long.toString(user.getId())).setIssuedAt(new Date())
                .setExpiration(tokenExpirationDate).claim("fullname", user.getFullName())
                .claim("roles", user.getRoles().stream().map(UserRole::name).collect(Collectors.joining(", ")))
                .compact();

    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecreto.getBytes())).build()
                .parseClaimsJws(token).getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecreto.getBytes(Charset.forName("UTF-8"))).build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.info("Token malformado: " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.info("Token expirado: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.info("Token JWT no soportado: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.info("JWT claims vacio");
        } catch (Exception ex) {
            log.info("Error inesperado" + ex);
        }
        return false;
    }

    public String generateTokenWithEmail(UserEntity user) {
        Date tokenExpirationDate = new Date(System.currentTimeMillis() + (this.jwtDurationToken * 1000));

        return Jwts.builder().signWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()), SignatureAlgorithm.HS256)
                .setHeaderParam("type", TOKEN_TYPE).setSubject(Long.toString(user.getId())).setIssuedAt(new Date())
                .setExpiration(tokenExpirationDate).claim("fullname", user.getFullName())
                .claim("roles", user.getRoles().stream().map(UserRole::name).collect(Collectors.joining(", ")))
                .compact();
    }

    public Long getJwtDurationToken() {
        return jwtDurationToken;
    }
}
