package com.akashk.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akashk.entity.EducationDetailsEntity;

public interface EducationRepositry extends JpaRepository<EducationDetailsEntity, Serializable>{

}
