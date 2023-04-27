package com.joseparra.usermicroservice.user.domain.model;

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
public class QualificationHotelUser {
	private String nameHotel;
	private int score;
	private String observation;
}
