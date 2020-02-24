package com.dealflowbus.databasemainreader.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "description")
public class Details {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_description")
	private int id;
	
	@Column(name = "description")
	private String description = "";
	
	@Column(name = "content")
	private String content = "";

	public Details() {
	}

	public Details(String description, String content) {
		this.description = description;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Details [id=" + id + ", description=" + description + ", content=" + content + "]";
	}
	
	
}
