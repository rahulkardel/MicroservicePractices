package com.rahul.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.shared.Application;
import com.rahul.dto.CreateUserModel;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	Environment env;
	
	@GetMapping
	public String getPortNo() {
		
		
		return "Hello User service working on port : "+ env.getProperty("local.server.port");
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CreateUserModel createUser(@Valid @RequestBody CreateUserModel createUserModel) {
		
		
		
		
		return null;
	}
}
