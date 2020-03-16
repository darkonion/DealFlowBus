package com.dealflowbus.databasemainreader.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dealflowbus.databasemainreader.exceptions.NoteNotFoundException;
import com.dealflowbus.databasemainreader.models.Lead;
import com.dealflowbus.databasemainreader.models.Note;
import com.dealflowbus.databasemainreader.repository.NoteRepository;
import com.dealflowbus.databasemainreader.services.LeadRetrieveService;

@RestController
@RequestMapping("/api")
public class NotesController {
	
	@Autowired
	private LeadRetrieveService leadRetrieveService;
	
	@Autowired
	private NoteRepository noteRepo;
	
	//get list of lead notes
	@GetMapping("/leads/{id}/notes")
	//@PreAuthorize("hasAuthority('read_lead')")
	public List<Note> getListOfLeadNotes(@PathVariable int id) {
		
		Lead lead = leadRetrieveService.retrieveLead(id);
		
		return lead.getNotes();
	}

	
	//posting new note
	@PostMapping("/leads/{id}/notes")
	//@PreAuthorize("hasAuthority('create_lead')")
	public Lead addNoteToLead(@PathVariable int id, @RequestBody Note note) {
		Lead lead = leadRetrieveService.retrieveLead(id);
		lead.setLastTouched(LocalDate.now());
		note.setIssueDate(LocalDate.now());
			
		lead.addNote(note);

		leadRetrieveService.saveLead(lead);
		
		return lead;
	}
	
	
	//deleting note by id
	@DeleteMapping("/leads/{id}/notes/{noteId}")
	//@PreAuthorize("hasAuthority('delete_lead')")
	public String deleteNoteFromLead(@PathVariable int id, @PathVariable int noteId) {
		
		//checking if note with such id exist
		if (!noteRepo.existsById(noteId)) {
			throw new NoteNotFoundException("Note with id: " + id + " dont exist");
		}
		
		noteRepo.deleteById(noteId);
			
		return "note with id: " + noteId + " deleted";
	}	
	
}
