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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "kid_details")
public class KidDetailsEntity implements Serializable{
	
	private static final long serialVersionUID = -9005696308437522332L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kid_det_id")
	private Integer kidDetailsId;
	@Column(name = "name")
	private String kidName;
	private Integer age;
	@Column(name = "kid_ssn")
	private String kidSSN;
	@ManyToOne
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
		return "KidDetailsEntity [kidDetailsId=" + kidDetailsId + ", kidName=" + kidName + ", age=" + age + ", kidSSN="
				+ kidSSN + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + "]";
	}
	
	
	
	
	
}
