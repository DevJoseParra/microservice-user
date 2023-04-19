package com.joseparra.usermicroservice.mapper;

import org.modelmapper.ModelMapper;

@FunctionalInterface
public interface IMapper {
	public ModelMapper getMapper();
}
