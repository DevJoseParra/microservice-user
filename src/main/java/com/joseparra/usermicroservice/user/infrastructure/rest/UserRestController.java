package com.joseparra.usermicroservice.user.infrastructure.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joseparra.usermicroservice.user.application.service.IUserService;
import com.joseparra.usermicroservice.user.domain.model.UserModel;
import com.joseparra.usermicroservice.user.infrastructure.dtos.UserDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/user/v1")
@Slf4j
public class UserRestController {
	@Autowired
	private ModelMapper mapperUser;
	@Autowired
	private IUserService userService;

	@PostMapping(value = "/create")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
		log.info("--CREATING USER--");
		if (bindingResult.hasErrors()) {
			StringBuilder messageErrors = this.messageErrors(bindingResult);
			log.error(messageErrors.toString());
			return new ResponseEntity<>(messageErrors, HttpStatus.BAD_REQUEST);
		}
		UserModel newUser = mapperUser.map(userDto, UserModel.class);
		newUser = userService.createUser(newUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapperUser.map(newUser, UserDto.class));

	}

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAllUsers() {
		log.info("--LISTING USERS--");
		List<UserModel> listUsers = userService.listAllUsers();
		if (listUsers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There are't users.");
		}
		return ResponseEntity.ok(listUsers.stream().map(userModel -> mapperUser.map(userModel, UserDto.class))
				.collect(Collectors.toList()));
	}

	@GetMapping(value = "/id/{userID}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userID") Long idUser) {
		log.info("--GETTING USER BY ID--");
		UserModel userModel = userService.getUserById(idUser);
		return ResponseEntity.ok(mapperUser.map(userModel, UserDto.class));
	}

	@GetMapping(value = "/name/{name}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("name") String name) {
		UserModel userModel = userService.findByName(name);
		return ResponseEntity.ok(mapperUser.map(userModel, UserDto.class));
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder messageErrors = this.messageErrors(bindingResult);
			log.error(messageErrors.toString());
			return new ResponseEntity<>(messageErrors, HttpStatus.BAD_REQUEST);
		}
		UserModel userModel = mapperUser.map(userDto, UserModel.class);
		userModel = userService.updateUser(userModel);
		return ResponseEntity.ok(mapperUser.map(userModel, UserDto.class));
	}

	@DeleteMapping("/delete/{idUser}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("idUser") Long idUser) {
		userService.deleteUserById(idUser);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private StringBuilder messageErrors(BindingResult bindingResult) {
		StringBuilder messageErrors = new StringBuilder("It can't register/update to the user. The user has "
				+ bindingResult.getFieldErrorCount() + " errors. ");
		List<ObjectError> listErrors = bindingResult.getAllErrors();
		int numberMessage = 1;
		for (ObjectError objectError : listErrors) {
			if (numberMessage < bindingResult.getFieldErrorCount()) {
				messageErrors.append(objectError.getDefaultMessage() + ", ");
			} else {
				messageErrors.append(objectError.getDefaultMessage() + ".");
			}
			numberMessage += 1;
		}
		return messageErrors;
	}
}
