package com.joseparra.usermicroservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.joseparra.usermicroservice.mapper.IMapper;

@Configuration
public class UserMicroserviceConfiguration {
	@Bean
	public ModelMapper mapper() {
		IMapper modelMapper = ModelMapper::new;
		return modelMapper.getMapper();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
