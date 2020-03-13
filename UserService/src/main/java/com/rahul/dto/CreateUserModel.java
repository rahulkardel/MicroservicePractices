package com.rahul.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserModel {

	@NotNull(message = "FirstName should not be null")
	@NotEmpty
	@Size(min = 2,message = "Atleast two character in firstName")
	private String firstName;
	
	@NotNull(message = "LastName should not be null")
	@Size(min = 2,message = "Atleast two character in firstName")
	private String lastName;
	
	@NotNull(message = "Email should not be null")
	@Email(message = "Should be in email")
	private String email;
	
	@NotNull(message = "Password should not be null")
	@Size(min = 2,message = "Atleast two character in firstName")
	private String password;
	
	
	private String userId;
	
}
