package com.akashk.rest.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.akashk.exception.ApplicationException;
import com.akashk.exception.CaseNotFoundException;
import com.akashk.exception.CitizenNotFoundException;
import com.akashk.exception.FailedCaseException;


@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler({CaseNotFoundException.class,CitizenNotFoundException.class,FailedCaseException.class})
	public ResponseEntity<ApplicationException> handleCaseNotFoundException(Exception ex){
		
		ApplicationException applicationException = new ApplicationException();
		
		if(ex instanceof CaseNotFoundException) {
		
		applicationException.setErrorCode("400");
		applicationException.setErrorDesp(ex.getMessage().toString());
		applicationException.setDate(LocalDate.now());
		return new ResponseEntity<ApplicationException>(applicationException,HttpStatus.BAD_REQUEST);
		
		}
		
		if(ex instanceof CitizenNotFoundException) {
			applicationException.setErrorCode("400");
			applicationException.setErrorDesp(ex.getMessage().toString());
			applicationException.setDate(LocalDate.now());
			return new ResponseEntity<ApplicationException>(applicationException,HttpStatus.BAD_REQUEST);
		} 
		
		if(ex instanceof FailedCaseException){
			applicationException.setErrorCode("500");
			applicationException.setErrorDesp(ex.getMessage().toString());
			applicationException.setDate(LocalDate.now());
			return new ResponseEntity<ApplicationException>(applicationException,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		applicationException.setErrorCode("500");
		applicationException.setErrorDesp(ex.getMessage().toString());
		applicationException.setDate(LocalDate.now());
		
		
		
		return new ResponseEntity<ApplicationException>(applicationException,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
