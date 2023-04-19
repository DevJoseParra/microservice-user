package com.joseparra.usermicroservice.user.application.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("The Resource %s was not found with field name %s and value %s", resourceName, fieldName,
				fieldValue));
	}

	private static final long serialVersionUID = 1L;

}
