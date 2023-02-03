package com.akashk.entity;



import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "educ_details")
public class EducationDetailsEntity implements Serializable{
	
	
	private static final long serialVersionUID = 3426191404106619171L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer educationDetailsId;
	@Column(name = "high_degree")
	private String highestDegree;
	@Column(name = "grad_year")
	//@DateTimeFormat(pattern = "YYYY")
	//private LocalDate graduationYear;
	private String graduationYear;
	@Column(name = "university_name")
	private String universityName;
	@OneToOne
	@JoinColumn(name = "case_id")
	@JsonBackReference
	private CaseDetailsEntity caseDetails;

	@CreationTimestamp
	private LocalDate createdDate;
	@UpdateTimestamp
	private LocalDate updatedDate;
	@Column(name = "create_by")
	private Integer createdBy;
	@Column(name = "updated_by")
	private Integer updatedBy;

	@Override
	public String toString() {
		return "EducationDetailsEntity [educationDetailsId=" + educationDetailsId + ", highestDegree=" + highestDegree
				+ ", graduationYear=" + graduationYear + ", universityName=" + universityName + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ "]";
	}

	
	
	
	
}
