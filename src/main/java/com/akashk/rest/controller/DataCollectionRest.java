package com.akashk.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akashk.binding.Case;
import com.akashk.binding.CaseDetails;
import com.akashk.binding.EducationDetails;
import com.akashk.binding.IncomeDetails;
import com.akashk.binding.KidDetails;
import com.akashk.service.DataCollectionService;

@RestController
@RequestMapping("/data")
public class DataCollectionRest {

	@Autowired
	private DataCollectionService dataCollectionService;
	
	Logger logger = LoggerFactory.getLogger(DataCollectionRest.class);

	@PostMapping("/check/{citizenId}/{caseWorkerId}")
	public ResponseEntity<Case> createCase(@PathVariable Integer citizenId,@PathVariable Integer caseWorkerId){
		
		logger.info(" createCase method start ");
		
		logger.info(" createCase method called service class method createCase ");
		Integer caseId = dataCollectionService.createCase(citizenId, caseWorkerId);
		
		logger.info(" createCase method called service class method getPlans ");
		List<String> plans = dataCollectionService.getPlans();
	
		logger.info(" createCase method ends ");
		return new ResponseEntity<>(new Case(caseId,plans),HttpStatus.CREATED);
	}
	
	@PostMapping("/plan/{caseId}/{planName}")
	public ResponseEntity<String> selectPlan(@PathVariable Integer caseId,@PathVariable String planName){
		
		logger.info(" selectPlan method start ");
		
		String selectedPlan = dataCollectionService.selectPlans(planName, caseId);
		
		logger.info(" selectPlan method ends ");
		
		return new ResponseEntity<>(selectedPlan,HttpStatus.OK);
	}
	
	@PostMapping("/education/{caseId}/{caseWorkerId}")
	public ResponseEntity<String> saveEducationDetails(@PathVariable Integer caseId,@PathVariable Integer caseWorkerId,@RequestBody EducationDetails educationDetails){
		
		logger.info(" saveEducationDetails method start ");
		
		String educationResponse = dataCollectionService.saveEducationdetails(caseId, educationDetails, caseWorkerId);
		
		logger.info(" saveEducationDetails method ends ");
		
		return new ResponseEntity<>(educationResponse,HttpStatus.OK);
	}
	
	@PostMapping("/income/{caseId}/{caseWorkerId}")
	public ResponseEntity<String> saveIncomeDetails(@PathVariable Integer caseId,@PathVariable Integer caseWorkerId,@RequestBody IncomeDetails incomeDetails){
		
		logger.info(" saveIncomeDetails method start ");
		
		String incomeResponse = dataCollectionService.saveIncomeDetails(caseId, incomeDetails, caseWorkerId);
		
		logger.info(" saveIncomeDetails method ends ");
		
		return new ResponseEntity<>(incomeResponse,HttpStatus.OK);
	}
	
	@PostMapping("/kids/{caseId}/{caseWorkerId}")
	public ResponseEntity<CaseDetails> saveKidDetails(@PathVariable Integer caseId,@PathVariable Integer caseWorkerId,@RequestBody List<KidDetails> kidsDetails){
		
		logger.info(" saveKidDetails method start ");
		
		CaseDetails caseDetails = dataCollectionService.saveKidsDetails(caseId, kidsDetails, caseWorkerId);
		
		logger.info(" saveKidDetails method ends ");
		
		return new ResponseEntity<>(caseDetails,HttpStatus.OK);
		
		
	}
	
	
	/*
	 * @GetMapping("/get/{caseId}") public ResponseEntity<CaseDetails>
	 * getCaseDetails(@PathVariable Integer caseId) {
	 * 
	 * logger.info(" getCaseDetails method start ");
	 * 
	 * CaseDetails caseDetails = dataCollectionService.getCaseDetails(caseId);
	 * 
	 * logger.info(" getCaseDetails method ends ");
	 * 
	 * return new ResponseEntity<CaseDetails>( caseDetails,HttpStatus.OK);
	 * 
	 * 
	 * }
	 */
	
	
}
