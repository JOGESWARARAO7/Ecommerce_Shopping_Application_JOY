package com.example.mainproject.joy.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtilJoy {

	 private String secretKey = "mysecret"; // Use a strong secret key

	    public String generateToken(String email,String username,long userId) {
	    	return Jwts.builder()
	                .setSubject(email)  // Ensure email is the subject
	                .claim("username", username)  // Store the username in a custom claim
	                .claim("userId", userId)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
	                .signWith(SignatureAlgorithm.HS256, secretKey)
	                .compact();
	    }
	    public String extractUsername(String token) {
	        return Jwts.parser()
	                .setSigningKey(secretKey)  // Use the same secret key for parsing
	                .parseClaimsJws(token)  // Parse the JWT token
	                .getBody()
	                .getSubject();  // Extract the username (email in this case) from the token
	    }
}

