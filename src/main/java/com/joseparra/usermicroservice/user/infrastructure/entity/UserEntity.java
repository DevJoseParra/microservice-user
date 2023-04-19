package com.joseparra.usermicroservice.user.infrastructure.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.joseparra.usermicroservice.abstractclass.entity.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity extends AbstractEntity {
	@NotBlank(message = "Name can't be blank")
	private String name;
	@NotBlank(message = "Surname can't be blank")
	private String surname;
	@Email(message = "Email isn't valid")
	@NotEmpty(message = "Email can't be empty")
	@Column(unique = true)
	private String email;
	@NotEmpty(message = "Information can't be empty")
	private String information;
}
