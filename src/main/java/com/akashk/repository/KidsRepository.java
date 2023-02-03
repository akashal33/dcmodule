package com.akashk.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akashk.entity.KidDetailsEntity;

public interface KidsRepository extends JpaRepository<KidDetailsEntity,Serializable>{

}
