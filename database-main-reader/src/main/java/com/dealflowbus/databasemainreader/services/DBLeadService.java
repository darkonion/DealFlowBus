package com.dealflowbus.databasemainreader.services;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dealflowbus.databasemainreader.exceptions.LeadNotFoundException;
import com.dealflowbus.databasemainreader.exceptions.WrongHTTPQueryFormula;
import com.dealflowbus.databasemainreader.models.Detail;
import com.dealflowbus.databasemainreader.models.Lead;
import com.dealflowbus.databasemainreader.repository.LeadRepository;

@Service
public class DBLeadService {
	
	@Autowired
	private LeadRepository leadRepo;
	
	//retrieving single lead
	public Lead retrieveLead(int id) {
		Optional<Lead> findById = leadRepo.findById(id);
		
		if (!findById.isPresent()) {
			throw new LeadNotFoundException("Lead with id: " + id + " dont exist");
		}
			//retrieving lead
			Lead lead = findById.get();
			
			return lead;
	}
	
	//retrieving leads for statistics-unit only
	public List<Lead> getAllLeadsDesc() {
		
		return leadRepo.findAllByOrderByLastTouchedDesc();
	}
	
	//query serach, returning list of results
	public List<Lead> querySearch(String query) {
		
		List<Lead> results = leadRepo.querySearch(query);
		
		return results;
	}
	
	//retrieving full list in form of pages, with filters
	public Page<Lead> getAllLeadsPageable(boolean invorder, int page, int limit, int filter) {
		
		Pageable pageable;	
		
		if (!invorder) {
			pageable = PageRequest.of(page, limit, Sort.by("lastTouched").descending());
		} else {
			pageable = PageRequest.of(page, limit, Sort.by("lastTouched").ascending());
		}
		
		if (filter == 1) {
			return leadRepo.findAllKicked(pageable);
		} else if (filter == 2) {
			return leadRepo.findAllInPortfolio(pageable);
		} else if (filter == 3) {
			return leadRepo.findAllInProgress(pageable);
		} else if (filter == 4) {
			return leadRepo.findAllActiveLeads(pageable);
		} else if (filter == 5) {
			return leadRepo.findAll(pageable);
		} else {
				//maybe it is worth to hardcode here Hystrix formula
				throw new WrongHTTPQueryFormula("Mapping parameters were wrong");
		}

	}
	
	//saving new lead
	public ResponseEntity<Lead> saveLead(Lead lead) {
		
		lead.setDateArrival(LocalDate.now());
		lead.setLastTouched(LocalDate.now());
		
		leadRepo.save(lead);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(lead.getId())
				.toUri();
		
		ResponseEntity<Lead> responseEntity = ResponseEntity.created(uri).build();
		
		return responseEntity;
				
	}
	
	//deleting lead
	public String deleteLead(int id) {
		
		//checking if lead with such id exists
		boolean existsById = leadRepo.existsById(id);
		
		if(!existsById) {
			throw new LeadNotFoundException("Lead with id: " + id + " dont exist");
		}
		
		leadRepo.deleteById(id);
		
		return "Lead with id - "+id+" was removed from system";
	}
	
	//updating lead
	public ResponseEntity<Lead> updateLead(Lead lead) {
		
		lead.setLastTouched(LocalDate.now());
		leadRepo.save(lead);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(lead.getId())
				.toUri();
		
		ResponseEntity<Lead> responseEntity = ResponseEntity.created(uri).build();
		
		return responseEntity;
	}
	
	//adding description to lead
	public ResponseEntity<Lead> addDescr(int id, Detail detail) {
		
		Lead lead = retrieveLead(id);
		
		lead.setLastTouched(LocalDate.now());	
		
		//retrieving current lead detail id
		int tempId = lead.getDetail().getDescId();
			
		//assigning current lead detail to new detail
		detail.setDescId(tempId);
			
		//setting new detail to retrieved lead
		lead.setDetail(detail);
				
		//commiting in database
		leadRepo.save(lead);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/")
				.buildAndExpand(lead.getId())
				.toUri();
		
		ResponseEntity<Lead> responseEntity = ResponseEntity.created(uri).build();
		
		return responseEntity;

	}

	
	
	
}










