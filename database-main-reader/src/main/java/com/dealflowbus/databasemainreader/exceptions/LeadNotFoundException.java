package com.dealflowbus.databasemainreader.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LeadNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5067778374150709784L;

	public LeadNotFoundException(String message) {
		super(message);
		
	}
	
	

}
