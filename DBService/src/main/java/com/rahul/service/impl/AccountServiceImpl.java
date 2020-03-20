package com.rahul.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahul.dto.AccountDto;
import com.rahul.entity.AccountEntity;
import com.rahul.repository.AccountRepository;
import com.rahul.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Override
	public List<AccountDto> getAccounts() {
	
		List<AccountEntity> accountEntityList= accountRepository.findAll();
		
		
		return null;
	}

}
