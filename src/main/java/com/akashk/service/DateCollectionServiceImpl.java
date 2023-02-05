package com.akashk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akashk.binding.CaseDetails;
import com.akashk.binding.EducationDetails;
import com.akashk.binding.IncomeDetails;
import com.akashk.binding.KidDetails;
import com.akashk.entity.CaseDetailsEntity;
import com.akashk.entity.CitizenEntity;
import com.akashk.entity.EducationDetailsEntity;
import com.akashk.entity.IncomeDetailsEntity;
import com.akashk.entity.KidDetailsEntity;
import com.akashk.exception.CaseNotFoundException;
import com.akashk.exception.CitizenNotFoundException;
import com.akashk.exception.FailedCaseException;
import com.akashk.repository.CaseRepository;
import com.akashk.repository.CitizenRepository;
import com.akashk.repository.EducationRepositry;
import com.akashk.repository.IncomeRepository;
import com.akashk.repository.KidsRepository;
import com.akashk.repository.PlanRepository;

@Service
public class DateCollectionServiceImpl implements DataCollectionService {

	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	private CaseRepository caseRepo;
	@Autowired
	private EducationRepositry educationRepo;
	@Autowired
	private IncomeRepository incomeRepo;
	@Autowired
	private KidsRepository kidRepo;
	@Autowired
	private PlanRepository planRepo;
	// @Autowired
	// private ApplicationRegistryFeignService applicationRegistryFeignService;

	Logger logger = LoggerFactory.getLogger(DataCollectionService.class);

	@Override
	@Transactional(readOnly = false)
	public Integer createCase(Integer citizenId, Integer caseworkerId) {

		logger.info("createCase method start ");

		CitizenEntity citizen = checkForCitizen(citizenId);

		CaseDetailsEntity caseDetails = new CaseDetailsEntity();
		caseDetails.setCitizen(citizen);
		caseDetails.setCreatedBy(caseworkerId);
		caseDetails.setUpdatedBy(caseworkerId);
		caseDetails = caseRepo.save(caseDetails);

		if (caseDetails.getCaseId() == null) {

			logger.info(" if block casedetails not saved ");

			throw new FailedCaseException(" failed to create case ");

		}

		logger.info(" createCase method end ");

		return caseDetails.getCaseId();
	}

	
	@Override
	public CitizenEntity checkForCitizen(Integer citizenId) {

		logger.info(" checkForCitizen method start ");

		Optional<CitizenEntity> citizen = citizenRepository.findById(citizenId);

		if(citizen.isEmpty()) {
			throw new CitizenNotFoundException("no ciziten found with id " + citizenId);
		}
		
		return citizen.get();
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getPlans() {

		logger.info(" getplans method start");

		List<String> plans = planRepo.findAll().stream().map(plan -> plan.getPlanName()).collect(Collectors.toList());

		logger.info(" getplans method end");

		return plans;
	}

	@Override
	@Transactional(readOnly = false)
	public String selectPlans(String planName, Integer caseId) {

		logger.info(" selectPlans method start");

		CaseDetailsEntity caseDetails = searchCase(caseId);

		caseDetails.setPlanName(planName);
		caseRepo.save(caseDetails);

		logger.info(" selectPlans method end");

		return planName + " plan selected ";
	}

	@Override
	public String saveEducationdetails(Integer caseId, EducationDetails educationDetails, Integer caseWorkerId) {

		logger.info(" saveEducationDetails method start");

		CaseDetailsEntity caseDetails = searchCase(caseId);
		EducationDetailsEntity educationDetailsEntity = new EducationDetailsEntity();
		BeanUtils.copyProperties(educationDetails, educationDetailsEntity);
		educationDetailsEntity.setCreatedBy(caseWorkerId);
		educationDetailsEntity.setUpdatedBy(caseWorkerId);
		educationDetailsEntity.setCaseDetails(caseDetails);
		educationRepo.save(educationDetailsEntity);

		logger.info(" saveEducationDetails method end");

		return " education details saved ";
	}

	@Override
	public String saveIncomeDetails(Integer caseId, IncomeDetails incomeDetails, Integer caseWorkerId) {

		logger.info(" saveIncomeDetails method start");

		CaseDetailsEntity caseDetails = searchCase(caseId);
		IncomeDetailsEntity incomeDetailsEntity = new IncomeDetailsEntity();
		BeanUtils.copyProperties(incomeDetails, incomeDetailsEntity);
		incomeDetailsEntity.setCreatedBy(caseWorkerId);
		incomeDetailsEntity.setUpdatedBy(caseWorkerId);
		incomeDetailsEntity.setCaseDetails(caseDetails);

		incomeRepo.save(incomeDetailsEntity);

		logger.info(" saveIncomeDetails method end");

		return " income details saved ";
	}

	@Override
	public CaseDetails saveKidsDetails(Integer caseId, List<KidDetails> kidsDetails, Integer caseWorkerId) {

		logger.info(" saveKidsDetails method start");

		CaseDetailsEntity caseDetails = searchCase(caseId);
		List<KidDetailsEntity> kidsEntities = new ArrayList<>();
		kidsDetails.stream().forEach(kidDetails -> {

			KidDetailsEntity kidDetailsEntity = new KidDetailsEntity();
			BeanUtils.copyProperties(kidDetails, kidDetailsEntity);
			kidDetailsEntity.setCreatedBy(caseWorkerId);
			kidDetailsEntity.setUpdatedBy(caseWorkerId);
			kidDetailsEntity.setCaseDetails(caseDetails);
			kidsEntities.add(kidDetailsEntity);
			//kidRepo.save(kidDetailsEntity);

		});
		kidRepo.saveAll(kidsEntities);
	
		logger.info(" saveKidsDetails method end");

		return getCaseDetails(caseId);
	}

	@Override
	public CaseDetails getCaseDetails(Integer caseId) {

		logger.info(" CaseDetails method start");

		CaseDetailsEntity caseDetailsEntity = searchCase(caseId);
		System.out.println(caseDetailsEntity);
		CaseDetails caseDetails = new CaseDetails();

		BeanUtils.copyProperties(caseDetailsEntity, caseDetails);
		caseDetails.setCitizenName(caseDetailsEntity.getCitizen().getFullName());
		caseDetails.setCitizenSsn(caseDetailsEntity.getCitizen().getSsnNo());
		logger.info(" CaseDetails method ends");

		return caseDetails;

	}

	public CaseDetailsEntity searchCase(Integer caseId) {

		logger.info(" searchCase method start");

		Optional<CaseDetailsEntity> caseDetails = caseRepo.findById(caseId);

		if (caseDetails.isEmpty()) {

			logger.info(" if block . case not found . searchCase method ends ");

			throw new CaseNotFoundException(" no case found with id " + caseId);

		}

		logger.info("  case  found . searchCase method ends ");

		return caseDetails.get();

	}

}
