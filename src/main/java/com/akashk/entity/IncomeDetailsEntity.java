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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "income_details")
public class IncomeDetailsEntity implements Serializable{
	
	
	private static final long serialVersionUID = 5219803469746750795L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "inc_det_id")
	private Integer incomeDetailsId;
	@Column(name = "monthly_sal")
	private Double monthlySalaryIncome;
	@Column(name = "rent_inc")
	private Double rentIncome;
	@Column(name = "property_inc")
	private Double propertyIncome;
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
		return "IncomeDetailsEntity [incomeDetailsId=" + incomeDetailsId + ", monthlySalaryIncome="
				+ monthlySalaryIncome + ", rentIncome=" + rentIncome + ", propertyIncome=" + propertyIncome
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + "]";
	}
	
	
}
