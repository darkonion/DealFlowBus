package com.dealflowbus.statisticsunit.models;

import java.time.LocalDate;

public class Lead {

	private int id;
	private String projectName;
	private String projectOwner;
	private boolean inProgress;
	private boolean rejected;
	private String field;
	private LocalDate dateArrival;
	private LocalDate lastTouched;

	public Lead() {
		
	}
	
	public Lead(int id, String projectName, String projectOwner, boolean inProgress, boolean rejected, String field,
			LocalDate dateArrival, LocalDate lastTouched) {
		this.id = id;
		this.projectName = projectName;
		this.projectOwner = projectOwner;
		this.inProgress = inProgress;
		this.rejected = rejected;
		this.field = field;
		this.dateArrival = dateArrival;
		this.lastTouched = lastTouched;
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
	
	
	
	
	
	
}
