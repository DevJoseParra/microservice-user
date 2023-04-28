package com.joseparra.usermicroservice.user.application.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.joseparra.usermicroservice.user.domain.model.Hotel;

@FeignClient(name = "HOTEL-MICROSERVICE")
public interface IHotelService {

	@GetMapping("/api/hotel/v1/id/{idHotel}")
	public Hotel getHotelById(@PathVariable("idHotel") Long id);

}
