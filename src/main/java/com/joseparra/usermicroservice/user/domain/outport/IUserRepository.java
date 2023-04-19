package com.joseparra.usermicroservice.user.domain.outport;

import java.util.List;

import com.joseparra.usermicroservice.user.domain.model.UserModel;

public interface IUserRepository {
	public List<UserModel> listAllUsers();

	public UserModel createUser(UserModel user);

	public UserModel getUserById(Long idUser);

	public UserModel updateUser(UserModel user);

	public void deleteUserById(Long idUser);

	public UserModel findByName(String name);
}
