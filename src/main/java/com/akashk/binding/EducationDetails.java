package com.akashk.binding;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EducationDetails {

	private String highestDegree;
	//@DateTimeFormat(pattern = "yyyy")
	//private LocalDate graduationYear;
	private String graduationYear;
	private String universityName;
	
}
