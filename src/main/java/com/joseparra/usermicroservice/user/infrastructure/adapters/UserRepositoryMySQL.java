package com.joseparra.usermicroservice.user.infrastructure.adapters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.joseparra.usermicroservice.user.domain.model.UserModel;
import com.joseparra.usermicroservice.user.domain.outport.IUserRepository;
import com.joseparra.usermicroservice.user.infrastructure.entity.UserEntity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class UserRepositoryMySQL implements IUserRepository {

	private final IUserRepositoryMySQL userRepositoryMySql;

	private final ModelMapper mapperUser;

	@Override
	public List<UserModel> listAllUsers() {
		return userRepositoryMySql.findAll().stream().map(userEntity -> mapperUser.map(userEntity, UserModel.class))
				.collect(Collectors.toList());
	}

	@Override
	public UserModel getUserById(Long idUser) {
		Optional<UserEntity> optionalUserEntity = userRepositoryMySql.findById(idUser);
		return this.findBy(optionalUserEntity);
	}

	@Override
	public UserModel updateUser(UserModel user) {
		Optional<UserEntity> optionalUserEntity = userRepositoryMySql.findById(user.getId());
		UserEntity updateUserEntity = null;
		if (optionalUserEntity.isPresent()) {
			updateUserEntity = optionalUserEntity.get();
			updateUserEntity.setEmail(user.getEmail());
			updateUserEntity.setName(user.getName());
			updateUserEntity.setInformation(user.getInformation());
			updateUserEntity.setSurname(user.getSurname());
			return mapperUser.map(userRepositoryMySql.save(updateUserEntity), UserModel.class);
		}
		return null;
	}

	@Override
	public UserModel createUser(UserModel user) {
		UserEntity userEntity = mapperUser.map(user, UserEntity.class);
		return mapperUser.map(userRepositoryMySql.save(userEntity), UserModel.class);
	}

	@Override
	public void deleteUserById(Long idUser) {
		userRepositoryMySql.deleteById(idUser);
	}

	@Override
	public UserModel findByName(String name) {
		Optional<UserEntity> optionalUserEntity = userRepositoryMySql.findByName(name);
		return this.findBy(optionalUserEntity);
	}

	private UserModel findBy(Optional<UserEntity> optional) {
		UserModel userModel = null;
		if (!optional.isEmpty()) {
			userModel = mapperUser.map(optional.get(), UserModel.class);
		}
		return userModel;
	}
}
