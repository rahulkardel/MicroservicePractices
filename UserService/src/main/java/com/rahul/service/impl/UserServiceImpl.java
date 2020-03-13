package com.rahul.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rahul.dto.CreateUserModel;
import com.rahul.entity.UserEntity;
import com.rahul.repository.UserRepository;
import com.rahul.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Override
	public CreateUserModel createUser(CreateUserModel createUserModel) {

		
		ModelMapper mapper = new ModelMapper();

		UserEntity userEntity = mapper.map(createUserModel, UserEntity.class);
		
		userEntity.setUserId(UUID.randomUUID().toString());
		userEntity.setPassword(encoder.encode(userEntity.getPassword()));
		userEntity = userRepository.save(userEntity);

		createUserModel.setPassword(null);
		createUserModel.setUserId(userEntity.getUserId());
		return createUserModel;
	}

	@Override
	public List<CreateUserModel> getUser() {
		
		List<UserEntity> userEntityList  = userRepository.findAll();
		
		List<CreateUserModel> createUserList = new ArrayList();
		
		ModelMapper mapper = new ModelMapper();

		if(userEntityList != null && !userEntityList.isEmpty()) {
			
			userEntityList.forEach(user -> {
				
				CreateUserModel createUserModel = mapper.map(user, CreateUserModel.class);
				createUserModel.setPassword(null);
				
				createUserList.add(createUserModel);
			});
		}
		
		return createUserList;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserEntity> optionalEntity = userRepository.findByEmail(username);
		
		if(!optionalEntity.isPresent()) {
			
			throw new UsernameNotFoundException(username);
			
		}
		UserEntity userEntity = optionalEntity.get();
		
		return new User(userEntity.getEmail(), userEntity.getPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		Optional<UserEntity> optionalEntity = userRepository.findByEmail(email);
		
		if(!optionalEntity.isPresent()) {
			
			throw new UsernameNotFoundException(email);
			
		}
		
		UserEntity userEntity = optionalEntity.get();
		
		
		return userEntity;
	}

}
