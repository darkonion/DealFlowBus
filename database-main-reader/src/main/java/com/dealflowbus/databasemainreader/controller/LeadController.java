package com.dealflowbus.databasemainreader.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dealflowbus.databasemainreader.models.Detail;
import com.dealflowbus.databasemainreader.models.Lead;
import com.dealflowbus.databasemainreader.models.LeadViews;
import com.dealflowbus.databasemainreader.services.DBLeadService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api")
public class LeadController {


	@Autowired
	private DBLeadService dBLeadService;
	

	//METHODS FOR LEAD AND DETAIL --------------------------------------------------------------

	
	//getting full lead list asc
	@JsonView(LeadViews.Base.class)
	@GetMapping(path = "/leadscrude")
	@PreAuthorize("hasAuthority('read_lead')")
	public List<Lead> getAllLeadsDesc() {	

		return dBLeadService.getAllLeadsDesc();
	}
	
	//getting search results
	@JsonView(LeadViews.List.class)
	@GetMapping(path = "/lsearch")
	@PreAuthorize("hasAuthority('read_lead')")
		public List<Lead> querySearch(@RequestParam(value = "query") String query) {	

		return dBLeadService.querySearch(query);
	}
	
	
	//getting leads with customizable filtering
	@JsonView(LeadViews.List.class)
	@GetMapping(path = "/leads")
	@PreAuthorize("hasAuthority('read_lead')")
	public Page<Lead> getAllLeadsPageable(@RequestParam(value = "l", defaultValue = "15") int limit,
											@RequestParam(value = "p", defaultValue = "0") int page,
											@RequestParam(value = "filter", required = false, defaultValue = "4") int filter,
											@RequestParam(value = "invorder", required = false) boolean invorder) {	
		
		return dBLeadService.getAllLeadsPageable(invorder, page, limit, filter);

	}
	
	
	//getting lead by id
	@GetMapping("/leads/{id}")
	@PreAuthorize("hasAuthority('read_lead')")
	public Lead getLead(@PathVariable int id) {
		Lead lead = dBLeadService.retrieveLead(id);
		
		return lead;
	}
	

	//Posting new Lead
	@PostMapping("/leads")
	@PreAuthorize("hasAuthority('create_lead')")
	public ResponseEntity<Lead> saveLead(@RequestBody Lead lead) {
	
		return dBLeadService.saveLead(lead);
	}
	
	
	//deleting lead
	@DeleteMapping("/leads/{id}")
	@PreAuthorize("hasAuthority('delete_lead')")
	public String deleteLead(@PathVariable int id) {
	
		return dBLeadService.deleteLead(id);
	}
	
	//updating lead
	@PutMapping("/leads")
	@PreAuthorize("hasAuthority('update_lead')")
	public ResponseEntity<Lead> updateLead(@RequestBody Lead lead) {
		
		return dBLeadService.updateLead(lead);
	}
	
	
	//updating Lead with detail infromation, or just updating detail
	@PutMapping("/leads/{id}/details")
	@PreAuthorize("hasAuthority('create_lead')")
	public ResponseEntity<Lead> addDescr(@PathVariable int id, @RequestBody Detail detail) {

		return dBLeadService.addDescr(id, detail);
	}
	
	
	//getting details by id
	@GetMapping("/leads/{id}/details")
	@PreAuthorize("hasAuthority('read_lead')")
	public Detail getDetail(@PathVariable int id) {
		Lead lead = dBLeadService.retrieveLead(id);
		return lead.getDetail();
	}
	

	

}








