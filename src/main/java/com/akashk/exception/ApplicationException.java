package com.akashk.exception;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ApplicationException {
	
	private String errorCode;
	private String errorDesp;
	private LocalDate date;

}
