package com.akashk.binding;

import java.util.List;

import lombok.Data;

@Data
public class Case {

	private Integer caseId;
	private List<String> planNames;
	
	public Case() {
		
	}
	
	public Case(Integer caseId, List<String> planNames) {
		super();
		this.caseId = caseId;
		this.planNames = planNames;
	}
	
	
	
}
