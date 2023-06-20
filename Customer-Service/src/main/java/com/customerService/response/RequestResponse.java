package com.customerService.response;

import org.springframework.http.HttpStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RequestResponse {

	private String message;
	private boolean success;
	private HttpStatus status;
	private Object result;

}