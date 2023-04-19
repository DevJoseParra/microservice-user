package com.joseparra.usermicroservice.user.infrastructure.adapters;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joseparra.usermicroservice.user.infrastructure.entity.UserEntity;

public interface IUserRepositoryMySQL extends JpaRepository<UserEntity, Long> {

	public Optional<UserEntity> findByName(String name);

}
