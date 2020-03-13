package com.rahul.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rahul.dto.CreateUserModel;
import com.rahul.entity.UserEntity;

public interface UserService extends UserDetailsService{

	
	public CreateUserModel createUser(CreateUserModel createUserModel);
	
	public List<CreateUserModel> getUser();
	
	public UserEntity getUserByEmail(String email);
}
