package com.dealflowbus.databasemainreader.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "main")
public class Lead {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "project_name")
	private String projectName;
	
	@Column(name = "project_owner")
	private String projectOwner;
	
	@Column(name = "in_progress")
	private boolean inProgress;
	
	@Column(name = "rejected")
	private boolean rejected;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="descr_id")
	private Details details;
	
	@Column(name = "field")
	private String field;
	
	@Column(name = "date_arrival")
	private LocalDate dateArrival = LocalDate.now();
	
	@Column(name = "last_touched")
	private LocalDate lastTouched = LocalDate.now();

	public Lead() {
	}

	public Lead(String projectName, String projectOwner, Details details,
			String field) {
		
		this.projectName = projectName;
		this.projectOwner = projectOwner;
		this.inProgress = false;
		this.rejected = false;
		this.details = details;
		this.field = field;
		this.lastTouched = LocalDate.now();
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

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
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

	@Override
	public String toString() {
		return "Lead [id=" + id + ", projectName=" + projectName + ", projectOwner=" + projectOwner + ", inProgress="
				+ inProgress + ", rejected=" + rejected + ", details=" + details + ", field=" + field + ", dateArrival="
				+ dateArrival + ", lastTouched=" + lastTouched + "]";
	}
	
	
	
	

}
