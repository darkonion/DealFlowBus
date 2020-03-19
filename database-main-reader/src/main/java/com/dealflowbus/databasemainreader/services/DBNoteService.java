package com.dealflowbus.databasemainreader.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dealflowbus.databasemainreader.exceptions.NoteNotFoundException;
import com.dealflowbus.databasemainreader.models.Lead;
import com.dealflowbus.databasemainreader.models.Note;
import com.dealflowbus.databasemainreader.repository.NoteRepository;

@Service
public class DBNoteService {

	@Autowired
	private DBLeadService dBLeadService;
	
	@Autowired
	private NoteRepository noteRepo;
	
	
	
	//getting list of notes in lead
	public List<Note> getListOfLeadNotes(int id) {
		
		Lead lead = dBLeadService.retrieveLead(id);
		
		return lead.getNotes();
	}
	
	//adding new note to lead
	public ResponseEntity<Lead> addNoteToLead(Note note, int id) {
		
		Lead lead = dBLeadService.retrieveLead(id);
		lead.setLastTouched(LocalDate.now());
		note.setIssueDate(LocalDate.now());
			
		lead.addNote(note);

		return dBLeadService.saveLead(lead);

	}
	
	//deleting particular note from lead
	public String deleteNoteFromLead(int id, int noteId) {
		
		//checking if note with such id exist
		if (!noteRepo.existsById(noteId)) {
			throw new NoteNotFoundException("Note with id: " + id + " dont exist");
		}
		
		noteRepo.deleteById(noteId);
		
		return "note with id: " + noteId + " deleted";
	}

}
