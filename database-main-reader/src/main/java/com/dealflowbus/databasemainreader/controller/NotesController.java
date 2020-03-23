package com.dealflowbus.databasemainreader.controller;

import com.dealflowbus.databasemainreader.models.Lead;
import com.dealflowbus.databasemainreader.models.Note;
import com.dealflowbus.databasemainreader.services.DBNoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotesController {

	private final DBNoteService noteService;

	public NotesController(DBNoteService noteService) {
		this.noteService = noteService;
	}

	//get list of lead notes
	@GetMapping("/leads/{id}/notes")
	@PreAuthorize("hasAuthority('read_lead')")
	public List<Note> getListOfLeadNotes(@PathVariable int id) {
		
		return noteService.getListOfLeadNotes(id);
		
	}

	
	//posting new note
	@PostMapping("/leads/{id}/notes")
	@PreAuthorize("hasAuthority('create_lead')")
	public ResponseEntity<Lead> addNoteToLead(@PathVariable int id, @RequestBody Note note) {

		return noteService.addNoteToLead(note, id);
	}
	
	
	//deleting note by id
	@DeleteMapping("/leads/{id}/notes/{noteId}")
	@PreAuthorize("hasAuthority('delete_lead')")
	public String deleteNoteFromLead(@PathVariable int id, @PathVariable int noteId) {

		return noteService.deleteNoteFromLead(id, noteId);
	}	
	
}
