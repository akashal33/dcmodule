package com.akashk.binding;

import java.util.Set;
import com.akashk.entity.EducationDetailsEntity;
import com.akashk.entity.IncomeDetailsEntity;
import com.akashk.entity.KidDetailsEntity;

import lombok.Data;

@Data
public class CaseDetails {

	private String planName;
	private EducationDetailsEntity educationDetails;
	private IncomeDetailsEntity incomeDetails;
	private Set<KidDetailsEntity> kidsDetails;
	
}
