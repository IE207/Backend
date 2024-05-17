package com.tananh.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrolDetail> UserExceptionHandler(UserException e, WebRequest req){
		ErrolDetail err = new ErrolDetail(e.getMessage(),req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrolDetail>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ChatException.class)
	public ResponseEntity<ErrolDetail> ChatExceptionHandler(ChatException e, WebRequest req){
		ErrolDetail err = new ErrolDetail(e.getMessage(),req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrolDetail>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MessageException.class)
	public ResponseEntity<ErrolDetail> MessageExceptionHandler(MessageException e, WebRequest req){
		ErrolDetail err = new ErrolDetail(e.getMessage(),req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrolDetail>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrolDetail> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, WebRequest req){
		ErrolDetail err = new ErrolDetail("Validation error",req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrolDetail>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrolDetail> NoHandlerFoundException(NoHandlerFoundException e, WebRequest req){
		ErrolDetail err = new ErrolDetail("endpoint error",req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrolDetail>(err,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrolDetail> otherExceptionHandler(Exception e, WebRequest req){
		ErrolDetail err = new ErrolDetail(e.getMessage(),req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrolDetail>(err,HttpStatus.BAD_REQUEST);
		
	}
}
