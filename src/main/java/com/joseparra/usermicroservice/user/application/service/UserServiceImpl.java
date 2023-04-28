package com.joseparra.usermicroservice.user.application.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.joseparra.usermicroservice.user.application.exceptions.BadRequestException;
import com.joseparra.usermicroservice.user.application.exceptions.ResourceNotFoundException;
import com.joseparra.usermicroservice.user.application.externalservices.IHotelService;
import com.joseparra.usermicroservice.user.domain.model.Qualification;
import com.joseparra.usermicroservice.user.domain.model.QualificationHotelUser;
import com.joseparra.usermicroservice.user.domain.model.UserModel;
import com.joseparra.usermicroservice.user.domain.outport.IUserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

	private IUserRepository userRepository;

	private RestTemplate restTemplate;

	private IHotelService hotelService;

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
		List<QualificationHotelUser> listQualificationHotelUser = Arrays.stream(restTemplate.getForObject(
				"http://QUALIFICATION-MICROSERVICE/api/qualification/v1/all_by_user/" + userModel.getId(),
				Qualification[].class)).collect(Collectors.toList()).stream().map(qualification -> {
					String hotelName = hotelService.getHotelById(qualification.getHotelId()).getName();
					return QualificationHotelUser.builder().nameHotel(hotelName).score(qualification.getScore())
							.observation(qualification.getObservation()).build();
				}).collect(Collectors.toList());

		userModel.setListQualification(listQualificationHotelUser);

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
