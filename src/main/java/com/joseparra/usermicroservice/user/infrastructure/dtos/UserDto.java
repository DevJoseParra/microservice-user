package com.joseparra.usermicroservice.user.infrastructure.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Long id;
	@NotBlank(message = "Name can't be blank")
	private String name;
	@NotBlank(message = "Surname can't be blank")
	private String surname;
	@Email(message = "Email isn't valid")
	@NotBlank(message = "Email can't be empty")
	private String email;
	@NotBlank(message = "Information can't be empty")
	private String information;
}
