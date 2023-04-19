package com.joseparra.usermicroservice.user.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.joseparra.usermicroservice.user.application.exceptions.BadRequestException;
import com.joseparra.usermicroservice.user.application.exceptions.ResourceNotFoundException;
import com.joseparra.usermicroservice.user.domain.model.UserModel;
import com.joseparra.usermicroservice.user.domain.outport.IUserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

	private IUserRepository userRepository;

	@Override
	public List<UserModel> listAllUsers() {
		return userRepository.listAllUsers();
	}

	@Override
	public UserModel getUserById(Long idUser) throws ResourceNotFoundException, BadRequestException {
		if (idUser == null) {
			throw new BadRequestException("Bad Request --> ID=" + idUser);
		}
		UserModel userModel = userRepository.getUserById(idUser);
		if (userModel == null) {
			throw new ResourceNotFoundException("User", "ID", String.valueOf(idUser));
		}
		return userModel;
	}

	@Override
	public UserModel updateUser(UserModel user) throws ResourceNotFoundException, BadRequestException {
		Long idUser = user.getId();
		this.validIdUser(idUser);
		return userRepository.updateUser(user);
	}

	@Override
	public UserModel createUser(UserModel user) {
		return userRepository.createUser(user);
	}

	@Override
	public void deleteUserById(Long idUser) {
		this.validIdUser(idUser);
		userRepository.deleteUserById(idUser);
	}

	@Override
	public UserModel findByName(String name) {
		if (name == null) {
			throw new BadRequestException("Bad Request --> NAME=" + name);
		}
		UserModel userModel = userRepository.findByName(name);
		if (userModel == null) {
			throw new ResourceNotFoundException("User", "NAME", String.valueOf(name));
		}
		return userModel;
	}

	private void validIdUser(Long idUser) {
		if (idUser == null) {
			throw new BadRequestException("Bad Request --> ID=" + idUser);
		} else {
			UserModel userModel = userRepository.getUserById(idUser);
			if (userModel == null) {
				throw new ResourceNotFoundException("User", "ID", String.valueOf(idUser));
			}
		}
	}
}
