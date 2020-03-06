package com.dealflowbus.databasemainreader.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealflowbus.commons.models.Lead;
import com.dealflowbus.databasemainreader.exceptions.LeadNotFoundException;
import com.dealflowbus.databasemainreader.repository.LeadRepository;

@Service
public class LeadRetrieveService {
	
	@Autowired
	private LeadRepository leadRepo;

	public Lead retrieveLead(int id) {
		Optional<Lead> findById = leadRepo.findById(id);
		
		if (!findById.isPresent()) {
			throw new LeadNotFoundException("Lead with id: " + id + " dont exist");
		}
			//retrieving lead
			Lead lead = findById.get();
			
			return lead;
	}
	
	public void saveLead(Lead lead) {
		leadRepo.save(lead);
	}
	
}
