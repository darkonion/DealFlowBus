package com.dealflowbus.databasemainreader.controller;


import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dealflowbus.databasemainreader.exceptions.LeadNotFoundException;
import com.dealflowbus.databasemainreader.exceptions.WrongHTTPQueryFormula;
import com.dealflowbus.databasemainreader.models.Detail;
import com.dealflowbus.databasemainreader.models.Lead;
import com.dealflowbus.databasemainreader.models.LeadViews;
import com.dealflowbus.databasemainreader.repository.LeadRepository;
import com.dealflowbus.databasemainreader.services.LeadRetrieveService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class LeadController {


	@Autowired
	private LeadRepository leadRepo;
	
	@Autowired
	private LeadRetrieveService leadRetrieveService;
	
	@GetMapping("/")
	private String testProbe() {
		return "{healthy:true}";
	}

	

	//METHODS FOR LEAD AND DETAIL --------------------------------------------------------------

	
	//getting full lead list asc
	@JsonView(LeadViews.Base.class)
	@GetMapping(path = "/api/leadscrude")
	//@PreAuthorize("hasAuthority('read_lead')")
	public List<Lead> getAllLeadsDesc() {	

		return leadRepo.findAllByOrderByLastTouchedDesc();
	}
	
	
	//getting search results
	@JsonView(LeadViews.List.class)
	@GetMapping(path = "/api/lsearch")
	//@PreAuthorize("hasAuthority('read_lead')")
		public List<Lead> querySearch(@RequestParam(value = "query") String query) {	

		return leadRepo.querySearch(query);
	}
	
	
	//getting leads with customizable filtering
	@JsonView(LeadViews.List.class)
	@GetMapping(path = "/api/leads")
	//@PreAuthorize("hasAuthority('read_lead')")
	public Page<Lead> getAllLeadsPageDesc(@RequestParam(value = "l", defaultValue = "15") int limit,
											@RequestParam(value = "p", defaultValue = "0") int page,
											@RequestParam(value = "filter", required = false, defaultValue = "4") int filter,
											@RequestParam(value = "invorder", required = false) boolean invorder) {	
		
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
	
	
	//getting lead by id
	@GetMapping("/api/leads/{id}")
	//@PreAuthorize("hasAuthority('read_lead')")
	public Lead getLead(@PathVariable int id) {
		Lead lead = leadRetrieveService.retrieveLead(id);
		
		return lead;
	}
	

	//Posting new Lead
	@PostMapping("/api/leads")
	//@PreAuthorize("hasAuthority('create_lead')")
	public ResponseEntity<Lead> saveLead(@RequestBody Lead lead) {
		
		lead.setDateArrival(LocalDate.now());
		lead.setLastTouched(LocalDate.now());
		System.out.println(lead.getDateArrival());
		Lead savedLead = leadRepo.save(lead);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											.path("/{id}")
											.buildAndExpand(savedLead.getId())
											.toUri();
		
		ResponseEntity<Lead> responseEntity = ResponseEntity.created(uri).build();
		
		return responseEntity;
	}
	
	
	//deleting lead
	@DeleteMapping("/api/leads/{id}")
	//@PreAuthorize("hasAuthority('delete_lead')")
	public String deleteLead(@PathVariable int id) {
			
		//checking if lead with such id exists
		boolean existsById = leadRepo.existsById(id);
		
		if(!existsById) {
				throw new LeadNotFoundException("Lead with id: " + id + " dont exist");
		}
			
		//deleting
		leadRepo.deleteById(id);

		return "Lead with id - "+id+" was removed from system";
	}

	

	@PutMapping("/api/leads")
	//@PreAuthorize("hasAuthority('update_lead')")
	public Lead updateLead(@RequestBody Lead lead) {
		lead.setLastTouched(LocalDate.now());
		leadRepo.save(lead);

		return lead;
	}
	
	
	//updating Lead with detail infromation
	@PutMapping("/api/leads/{id}/details")
	//@PreAuthorize("hasAuthority('update_lead')")
	public Lead addDescr(@PathVariable int id, @RequestBody Detail detail) {
			
		Lead lead = leadRetrieveService.retrieveLead(id);
			
		//updating last touched of lead
		lead.setLastTouched(LocalDate.now());	
			
		//retrieving current lead detail id
		int tempId = lead.getDetail().getDescId();
			
		//assigning current lead detail to new detail
		detail.setDescId(tempId);
			
		//setting new detail to retrieved lead
		lead.setDetail(detail);
				
		//commiting in database
		leadRepo.save(lead);
		return lead;
	}
	
	
	//getting details by id
	@GetMapping("/api/leads/{id}/details")
	//@PreAuthorize("hasAuthority('read_lead')")
	public Detail getDetail(@PathVariable int id) {
		Lead lead = leadRetrieveService.retrieveLead(id);
		return lead.getDetail();
	}
	

	

}








