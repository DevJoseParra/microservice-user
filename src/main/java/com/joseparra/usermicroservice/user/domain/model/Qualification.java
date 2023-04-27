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
public class Qualification {
	private Long userId;
	private Long hotelId;
	private int score;
	private String observation;

}
