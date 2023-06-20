package com.customerService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<UserExistedResponse> handleException(UserAlreadyExistedException existedException) {
		// create a studentErrorResponse
		UserExistedResponse userExistedResponse = new UserExistedResponse();

		userExistedResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		userExistedResponse.setMessage(existedException.getMessage());
		userExistedResponse.setTimeStamp(System.currentTimeMillis());
		// return responseEntity
		return new ResponseEntity<>(userExistedResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<NotFoundResponse> handleException(UserNotFoundException notFoundException) {
		// create a studentErrorResponse
		NotFoundResponse notFoundResponse = new NotFoundResponse();
		notFoundResponse.setStatus(HttpStatus.NOT_FOUND.value());
		notFoundResponse.setMessage(notFoundException.getMessage());
		notFoundResponse.setTimeStamp(System.currentTimeMillis());
		// return responseEntity
		return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<WrongCredentialExceptionResponse> handlException(WrongCredentialEXception notFoundException) {
		// create a studentErrorResponse
		WrongCredentialExceptionResponse wrongCredentialExceptionResponse = new WrongCredentialExceptionResponse();
		wrongCredentialExceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
		wrongCredentialExceptionResponse.setMessage(notFoundException.getMessage());
		wrongCredentialExceptionResponse.setTimeStamp(System.currentTimeMillis());
		// return responseEntity
		return new ResponseEntity<>(wrongCredentialExceptionResponse, HttpStatus.NOT_ACCEPTABLE);
	}
}
