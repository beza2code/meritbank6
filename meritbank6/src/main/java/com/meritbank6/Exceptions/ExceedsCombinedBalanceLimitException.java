package com.meritbank6.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceedsCombinedBalanceLimitException extends Exception {
	
	public ExceedsCombinedBalanceLimitException(String message) { super(message); 
	}
}
