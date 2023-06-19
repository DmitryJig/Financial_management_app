package com.finance.app.security.jwt;

import com.finance.app.model.dto.LoginResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtService {
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    static final String PREFIX = "Bearer";
    private static Integer expirationTime = 1000 * 60 * 100;

    public LoginResponse getLoginResponse(JwtUser jwtUser) {
        return new LoginResponse(
                jwtUser.getId(),
                getToken(jwtUser)
        );
    }


    private String getToken(JwtUser user) {
        String username = user.getUsername();
        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        Map<String, Object> claims = new HashMap<>(Map.of("authority", authorities));

        return Jwts.builder()
                .setClaims(claims)
                .claim("id", user.getId())
                .claim("profileId", user.getProfilesId())
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null) {
            String user = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();
            if (user != null) return user;
        }
        return null;
    }

    public Collection<? extends GrantedAuthority> getAuthority(String token) {
        Claims claims = getAllClaimsFromToken(token);
        List<String> roles = claims.get("authority", List.class);
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.replace(PREFIX, ""))
                .getBody();
    }

}
