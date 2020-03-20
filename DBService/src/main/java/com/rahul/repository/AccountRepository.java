package com.rahul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>{

}
