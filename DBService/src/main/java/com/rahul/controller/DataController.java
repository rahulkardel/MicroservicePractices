package com.rahul.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.dto.AccountDto;

@RestController
@RequestMapping("/")
public class DataController {

	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AccountDto> getAccountDetails(){
		
		
		return null;
	}
}
