package com.customerService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookRestExceptionHandler {



	@ExceptionHandler
	public ResponseEntity<QuantityNotAvailable> handleException(QuantityNotAvailableException notFoundException) {
		// create a studentErrorResponse
		QuantityNotAvailable notFoundResponse = new QuantityNotAvailable();
		notFoundResponse.setStatus(HttpStatus.NOT_FOUND.value());
		notFoundResponse.setMessage(notFoundException.getMessage());
		notFoundResponse.setTimeStamp(System.currentTimeMillis());
		// return responseEntity
		return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
	}
	
}
