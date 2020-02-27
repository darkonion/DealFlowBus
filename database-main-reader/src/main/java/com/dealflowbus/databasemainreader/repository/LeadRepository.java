package com.dealflowbus.databasemainreader.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dealflowbus.databasemainreader.entities.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
	

	
	// MUST HAVE FOR STATISTICS -------------------------------
	List<Lead> findAllByOrderByLastTouchedAsc();
	List<Lead> findAllByOrderByLastTouchedDesc();
	//---------------------------------------------------------
	
	//FOR WEB GUI ---------------------------------------------
	Page<Lead> findAll(Pageable pageable);
	
	@Query("SELECT l FROM Lead l where l.rejected=true")
	Page<Lead> findAllKicked(Pageable pageable);
	
	@Query("SELECT l FROM Lead l where l.inPortfolio=true")
	Page<Lead> findAllInPortfolio(Pageable pageable);
	
	@Query("SELECT l FROM Lead l where l.inProgress=true and l.rejected=false")
	Page<Lead> findAllInProgress(Pageable pageable);
	
	@Query("SELECT l FROM Lead l where l.inPortfolio=false and l.rejected=false")
	Page<Lead> findAllActiveLeads(Pageable pageable);
	
	@Query("SELECT l FROM Lead l where LOWER(l.projectName) like LOWER(:query) OR LOWER(l.projectOwner)=LOWER(:query)")
	List<Lead> querySearch(String query);
	//---------------------------------------------------------

}
