package com.dealflowbus.statisticsunit.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Lead implements Serializable {

	
	private boolean inProgress;
	private boolean rejected;
	private String field;
	private LocalDate dateArrival;
	private LocalDate lastTouched;
	private boolean inPortfolio;

	public Lead() {
		
	}
	
	public Lead(boolean inProgress, boolean rejected, String field,
			LocalDate dateArrival, LocalDate lastTouched, boolean inPortfolio) {
		
		this.inProgress = inProgress;
		this.rejected = rejected;
		this.field = field;
		this.dateArrival = dateArrival;
		this.lastTouched = lastTouched;
		this.inPortfolio = inPortfolio;
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

	public boolean isInPortfolio() {
		return inPortfolio;
	}

	public void setInPortfolio(boolean inPortfolio) {
		this.inPortfolio = inPortfolio;
	}

	
}
