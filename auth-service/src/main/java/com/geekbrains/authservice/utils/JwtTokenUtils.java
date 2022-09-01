package com.geekbrains.authservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils {


    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Long lifetime;


    public String generateToken (UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        claims.put("roles",roles);

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime()+ lifetime);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setClaims(claims)
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    private Claims getAllClaimsFromToken (String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

    }

    public String getUsernameFromToken (String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRolesFromToken (String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.get("roles",List.class);
    }
}
