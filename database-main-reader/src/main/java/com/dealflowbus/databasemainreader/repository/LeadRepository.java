package com.dealflowbus.databasemainreader.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dealflowbus.databasemainreader.entities.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
	
	List<Lead> findAllByOrderByLastTouchedAsc();
	List<Lead> findAllByOrderByLastTouchedDesc();

}
