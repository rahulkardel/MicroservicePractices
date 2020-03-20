package com.rahul.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	public Optional<UserEntity> findByEmail(String email);
}
