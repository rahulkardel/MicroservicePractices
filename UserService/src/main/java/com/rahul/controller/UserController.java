package com.rahul.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.shared.Application;
import com.rahul.dto.CreateUserModel;
import com.rahul.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	Environment env;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(value="/port", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getPort() {
		
		
		return " Hello User service working on port : "+ env.getProperty("local.server.port")
		+ "  token time : "+ env.getProperty("token.expiration.time")
		+ " Token value is : " + env.getProperty("token.secret");
		
		//return userService.getUser();
	}
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CreateUserModel> getUser() {
		
		
		//return "Hello User service working on port : "+ env.getProperty("local.server.port");
		
		return userService.getUser();
	}
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public void createUser(@Valid @RequestBody CreateUserModel createUserModel) {
		
		userService.createUser(createUserModel);
		//return new ResponseEntity(HttpStatus.CREATED.value());
	}
}
