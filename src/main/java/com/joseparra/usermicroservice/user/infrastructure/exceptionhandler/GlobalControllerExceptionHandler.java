package com.joseparra.usermicroservice.user.infrastructure.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.joseparra.usermicroservice.user.application.exceptions.BadRequestException;
import com.joseparra.usermicroservice.user.application.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
		return ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value()).timestamp(new Date())
				.message(ex.getMessage()).description(request.getDescription(false)).build();
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage badRequestException(Exception ex, WebRequest request) {
		return ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value()).timestamp(new Date())
				.message(ex.getMessage()).description(request.getDescription(false)).build();
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage resourceNotFoundException(Exception ex, WebRequest request) {
		return ErrorMessage.builder().statusCode(HttpStatus.NOT_FOUND.value()).timestamp(new Date())
				.message(ex.getMessage()).description(request.getDescription(false)).build();
	}
}
