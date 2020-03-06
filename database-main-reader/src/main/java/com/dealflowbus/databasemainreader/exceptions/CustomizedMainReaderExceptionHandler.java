package com.dealflowbus.databasemainreader.exceptions;



import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedMainReaderExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExc(Exception ex, WebRequest request) {
		
		ExceptionResponder exceptionResponse = new ExceptionResponder(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(LeadNotFoundException.class)
	public final ResponseEntity<Object> handleLeadNotFoundExc(LeadNotFoundException ex, WebRequest request) {
		
		ExceptionResponder exceptionResponse = new ExceptionResponder(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoteNotFoundException.class)
	public final ResponseEntity<Object> handleNOteNotFoundExc(NoteNotFoundException ex, WebRequest request) {
		
		ExceptionResponder exceptionResponse = new ExceptionResponder(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(WrongHTTPQueryFormula.class)
	public final ResponseEntity<Object> handleWrongHTTPQuery(WrongHTTPQueryFormula ex, WebRequest request) {
		
		ExceptionResponder exceptionResponse = new ExceptionResponder(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MyFileNotFoundException.class)
	public final ResponseEntity<Object> handleFileNotFoundExc(MyFileNotFoundException ex, WebRequest request) {
		
		ExceptionResponder exceptionResponse = new ExceptionResponder(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FileStorageException.class)
	public final ResponseEntity<Object> handleFileStorageExc(FileStorageException ex, WebRequest request) {
		
		ExceptionResponder exceptionResponse = new ExceptionResponder(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INSUFFICIENT_STORAGE);
	}
	
	
	
	
}
