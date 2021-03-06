package com.dealflowbus.databasemainreader.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notes")
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id")
	private int noteId;
	
	@Column(name = "note")
	private String note;
		
	@Column(name = "issue_date")
	private LocalDate issueDate = LocalDate.now();

	public Note() {

	}

	public Note(String note, Lead lead, LocalDate issueDate) {
		this.note = note;
		this.issueDate = issueDate;
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

	@Override
	public String toString() {
		return "Note [note=" + note + ", issueDate=" + issueDate + "]";
	}
	
	
	
	
	
	

}
