package com.akashk.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akashk.entity.IncomeDetailsEntity;

public interface IncomeRepository extends JpaRepository<IncomeDetailsEntity, Serializable>{

}
