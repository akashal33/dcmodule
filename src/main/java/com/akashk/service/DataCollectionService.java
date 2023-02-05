package com.akashk.service;

import java.util.List;

import com.akashk.binding.CaseDetails;
import com.akashk.binding.EducationDetails;
import com.akashk.binding.IncomeDetails;
import com.akashk.binding.KidDetails;
import com.akashk.entity.CitizenEntity;

public interface DataCollectionService {
	
	
	public Integer createCase(Integer citizenId,Integer caseworkerId);
	public CitizenEntity checkForCitizen(Integer citizenId);
	public List<String> getPlans();
	public String selectPlans(String planName,Integer caseId);
	public String saveEducationdetails(Integer caseId,EducationDetails educationDetails,Integer caseWorkerId);
	public String saveIncomeDetails(Integer caseId,IncomeDetails incomeDetails,Integer caseWorkerId);
	public CaseDetails saveKidsDetails(Integer caseId,List<KidDetails> kidsDetails,Integer caseWorkerId);
	public CaseDetails getCaseDetails(Integer caseId); 

}
