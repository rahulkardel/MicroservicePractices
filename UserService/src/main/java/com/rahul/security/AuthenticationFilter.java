package com.rahul.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahul.dto.LoginRequestDto;
import com.rahul.entity.UserEntity;
import com.rahul.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private UserService userService;
	
	private Environment env;
	
	public AuthenticationFilter(UserService userService, Environment env) {
		
		this.userService= userService;
		this.env = env;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException{
		
		try {
			
			LoginRequestDto credentials = new ObjectMapper().readValue(
					req.getInputStream(), LoginRequestDto.class);
			
			
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword(), new ArrayList()));
			
		}catch(IOException io) {
			
			throw new RuntimeException();
		}
		
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
			FilterChain chain, Authentication auth) throws ServletException, IOException {
		
		String userName = ((User) auth.getPrincipal()).getUsername();
		
		UserEntity userEntity = userService.getUserByEmail(userName);
		
		
		String token = Jwts.builder()
		.setSubject(userEntity.getUserId())
		.setExpiration(new Date(System.currentTimeMillis() + 
				Long.parseLong(env.getProperty("token.expiration.time"))))
		.signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
		.compact();
		
		res.addHeader("token", token);
		res.addHeader("userId", userEntity.getUserId());
		
	}
}
