package com.joseparra.usermicroservice.user.application.service;

import java.util.List;

import com.joseparra.usermicroservice.user.application.exceptions.BadRequestException;
import com.joseparra.usermicroservice.user.application.exceptions.ResourceNotFoundException;
import com.joseparra.usermicroservice.user.domain.model.UserModel;

public interface IUserService {
	public List<UserModel> listAllUsers();

	public UserModel createUser(UserModel user);

	public UserModel getUserById(Long idUser) throws ResourceNotFoundException;

	public UserModel updateUser(UserModel user) throws ResourceNotFoundException, BadRequestException;

	public void deleteUserById(Long idUser) throws ResourceNotFoundException;

	public UserModel findByName(String name) throws ResourceNotFoundException;
}
