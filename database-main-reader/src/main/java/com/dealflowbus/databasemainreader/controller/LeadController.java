package com.dealflowbus.databasemainreader.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dealflowbus.databasemainreader.entities.Detail;
import com.dealflowbus.databasemainreader.entities.Lead;
import com.dealflowbus.databasemainreader.repository.LeadRepository;

@RestController
@RequestMapping("/api")
public class LeadController {

	@Autowired
	private LeadRepository leadRepo;
	
	@GetMapping("/leads")
	private List<Lead> getAllLeads() {
		return leadRepo.findAll();
	}
	
	@GetMapping("/leads/{id}")
	private Lead getLead(@PathVariable int id) {
		Optional<Lead> findById = leadRepo.findById(id);
		
		if (!findById.isPresent()) {
			//throw new UserNotFoundException
		}
			//add hateoas
		return findById.get();
	}
	
	@PostMapping("/leads")
	private ResponseEntity<Lead> saveLead(@RequestBody Lead lead) {
		Lead savedLead = leadRepo.save(lead);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											.path("/{id}")
											.buildAndExpand(savedLead.getId())
											.toUri();
		
		ResponseEntity<Lead> responseEntity = ResponseEntity.created(uri).build();
		
		return responseEntity;
	}
	
	@PostMapping("/leads/{id}")
	private Lead addDescr(@PathVariable int id, @RequestBody Detail detail) {
		Optional<Lead> findById = leadRepo.findById(id);
		
		if (!findById.isPresent()) {
			//throw new UserNotFoundException
		}
			Lead lead = findById.get();
			detail.setDescId(id);
			lead.setDetail(detail);
			return lead;
	}
	
	
	
	
	@DeleteMapping("/leads/{id}")
	private String deleteLead(@PathVariable int id) {
		
		boolean existsById = leadRepo.existsById(id);
		
		if(!existsById) {
			//throw new UserNotFoundException
		}
		
		leadRepo.deleteById(id);

		return "Lead with id - "+id+" was removed from system";
	}
}














