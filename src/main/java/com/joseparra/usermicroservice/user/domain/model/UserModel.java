package com.joseparra.usermicroservice.user.domain.model;

import com.joseparra.usermicroservice.abstractclass.model.AbstractModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel extends AbstractModel {

	private String name;
	private String surname;
	private String email;
	private String information;

}
