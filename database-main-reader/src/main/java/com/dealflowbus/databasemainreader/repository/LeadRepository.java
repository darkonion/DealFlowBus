package com.dealflowbus.databasemainreader.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dealflowbus.databasemainreader.entities.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
	
	@Query("SELECT l FROM Lead l where l.inProgress=false and l.rejected=false order by lastTouched DESC")
	List<Lead> findActiveLeadsAscending();
	
	List<Lead> findAllByOrderByLastTouchedAsc();
	
	List<Lead> findAllByOrderByLastTouchedDesc();

}
