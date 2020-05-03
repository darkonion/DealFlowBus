package com.dealflowbus.databasemainreader.services;

import com.dealflowbus.databasemainreader.exceptions.NoteNotFoundException;
import com.dealflowbus.databasemainreader.models.Lead;
import com.dealflowbus.databasemainreader.models.Note;
import com.dealflowbus.databasemainreader.repository.NoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DBNoteService {

	private final DBLeadService dBLeadService;
	private final NoteRepository noteRepo;


	public DBNoteService(DBLeadService dBLeadService, NoteRepository noteRepo) {
		this.dBLeadService = dBLeadService;
		this.noteRepo = noteRepo;
	}


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

		lead.getNotes().add(note);

		return dBLeadService.updateLead(lead);

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
