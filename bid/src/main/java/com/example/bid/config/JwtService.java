package com.example.bid.config;

import com.example.bid.entity.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret; // Use the same key for signing and verification

    private static final long EXPIRATION_TIME = 86400000;  // 1 day in milliseconds

    // Method to extract username from JWT token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Method to extract claims from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Method to parse and extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // Use getSigningKey() for consistent key access
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(AppUser appUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", appUser.getEmail());
       // claims.put("role", appUser.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(appUser.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())  // Use secure key here
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails UserDetails) {
        // Extract the username from the token and check if it matches the expected one
        String tokenUsername = extractUsername(token);

        // Check if the token has expired
        Date expirationDate = extractClaim(token, Claims::getExpiration);
        boolean isExpired = expirationDate.before(new Date());

        return (tokenUsername.equals(UserDetails.getUsername()) && !isExpired);
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String getUserEmail(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String jwtToken;
            if (StringUtils.isEmpty(token))
                return null;
            jwtToken = token.replace("Bearer", "").trim();
            Claims claims = extractAllClaims(jwtToken);
            return (String) claims.get("userName");
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }
}