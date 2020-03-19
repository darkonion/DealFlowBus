package com.dealflowbus.statisticsunit.models;

import java.time.LocalDate;

//currently useless
public class Note {
	
	private String note;
	private LocalDate issueDate = LocalDate.now();
	private int noteId;
	
	public Note() {

	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	
	
	
	

}
