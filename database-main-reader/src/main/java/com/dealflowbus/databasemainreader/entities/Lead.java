package com.dealflowbus.databasemainreader.entities;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@JsonIgnoreProperties("hibernateLazyInitializer")
@Entity
@Table(name = "main")
public class Lead {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Size(min = 2, message = "Name should be at least 2 characters long")
	@Size(max = 40, message = "Too long name, max 40 characters")
	@JsonView(LeadViews.List.class)
	@Column(name = "project_name")
	private String projectName;
	
	@NotNull
	@Size(min = 2, message = "Owner should be at least 2 characters long")
	@Size(max = 40, message = "Too long name of Owner, max 40 characters")
	@JsonView(LeadViews.List.class)
	@Column(name = "project_owner")
	private String projectOwner;
	
	@JsonView(LeadViews.Base.class)
	@Column(name = "field")
	private String field;
	
	@NotNull
	@JsonView(LeadViews.List.class)
	@Column(name = "email")
	private String email;
	
	@JsonView(LeadViews.List.class)
	@Column(name = "extra_address")
	private String extraAddress;
	
	@JsonView(LeadViews.Base.class)
	@Column(name = "in_progress")
	private boolean inProgress;
	
	@JsonView(LeadViews.Base.class)
	@Column(name = "rejected")
	private boolean rejected;
	
	@JsonView(LeadViews.Base.class)
	@Column(name = "in_portfolio")
	private boolean inPortfolio;	
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="descr_id")
	private Detail detail;
	
	@JsonView(LeadViews.Base.class)
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "date_arrival")
	private LocalDate dateArrival;
	
	@JsonView(LeadViews.Base.class)
	@Column(name = "last_touched")
	private LocalDate lastTouched;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "lead_id")
	private List<Note> notes;

	public Lead() {
		
	}

	public Lead(String projectName, String projectOwner,
			String field, String email, String extraAddress) {
		
		this.projectName = projectName;
		this.projectOwner = projectOwner;
		this.inProgress = false;
		this.rejected = false;
		this.inPortfolio= false;
		this.detail = new Detail();
		this.field = field;
		this.email = email;
		this.extraAddress = extraAddress;
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectOwner() {
		return projectOwner;
	}

	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}

	public boolean isRejected() {
		return rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public LocalDate getDateArrival() {
		return dateArrival;
	}

	public void setDateArrival(LocalDate dateArrival) {
		this.dateArrival = dateArrival;
	}

	public LocalDate getLastTouched() {
		return lastTouched;
	}

	public void setLastTouched(LocalDate lastTouched) {
		this.lastTouched = lastTouched;
	}
	

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	public void addNote(Note note) {
		if (notes == null) {
			notes = new ArrayList<Note>();
		}
		notes.add(note);
	}

	public boolean isInPortfolio() {
		return inPortfolio;
	}

	public void setInPortfolio(boolean inPortfolio) {
		this.inPortfolio = inPortfolio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExtraAddress() {
		return extraAddress;
	}

	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}
	


}
