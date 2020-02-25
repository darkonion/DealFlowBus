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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dealflowbus.databasemainreader.entities.Detail;
import com.dealflowbus.databasemainreader.entities.Lead;
import com.dealflowbus.databasemainreader.entities.Note;
import com.dealflowbus.databasemainreader.repository.LeadRepository;
import com.dealflowbus.databasemainreader.repository.NoteRepository;

@RestController
@RequestMapping("/api")
public class LeadController {

	@Autowired
	private LeadRepository leadRepo;
	
	@Autowired
	private NoteRepository noteRepo;
	
	
	
	
	
	//METHODS FOR LEAD AND DETAIL --------------------------------------------------------------
	
	
	//getting lead by id
	@GetMapping("/leads")
	private List<Lead> getAllLeads() {
		
		return leadRepo.findAll();
	}
	
	
	//getting lead list
	@GetMapping("/leads/{id}")
	private Lead getLead(@PathVariable int id) {
		
		Lead lead = retrieveLead(id);
		
		return lead;
	}
	

	//Posting new Lead
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
	
	
	//updating Lead with detail infromation
		@PutMapping("/leads/{id}/details")
		private Lead addDescr(@PathVariable int id, @RequestBody Detail detail) {
			
			Lead lead = retrieveLead(id);
				
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
	
	
	//deleting lead
	@DeleteMapping("/leads/{id}")
	private String deleteLead(@PathVariable int id) {
		
		boolean existsById = leadRepo.existsById(id);
		
		if(!existsById) {
			//throw new UserNotFoundException
		}
		
		leadRepo.deleteById(id);

		return "Lead with id - "+id+" was removed from system";
	}

	
	
	
	
	
	//METHODS FOR NOTES --------------------------------------------------------------
	
	//posting new note
	@PostMapping("/leads/{id}/notes")
	private Lead addNoteToLead(@PathVariable int id, @RequestBody Note note) {
		Lead lead = retrieveLead(id);
		
		lead.addNote(note);

		leadRepo.save(lead);
		
		return lead;
	}
	
	//deleting note by id
	@DeleteMapping("/leads/{id}/notes/{noteId}")
	private String deleteNoteFromLead(@PathVariable int id, @PathVariable int noteId) {
		
		//checking if note with such id exist
		if (!noteRepo.existsById(noteId)) {
			//throw new NoteNotFoundException
		}
		
		noteRepo.deleteById(noteId);
			
		return "note with id: " + noteId + " deleted";
	}


	
	
	
	
	//HELPER METHODS --------------------------------------------------------------
	
	private Lead retrieveLead(int id) {
		Optional<Lead> findById = leadRepo.findById(id);
		
		if (!findById.isPresent()) {
			//throw new UserNotFoundException
		}
			//retrieving lead
			Lead lead = findById.get();
			
			return lead;
	}
	
}














