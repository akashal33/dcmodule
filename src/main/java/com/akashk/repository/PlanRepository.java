package com.akashk.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akashk.entity.PlanEntity;

public interface PlanRepository extends JpaRepository<PlanEntity, Serializable> {

}
