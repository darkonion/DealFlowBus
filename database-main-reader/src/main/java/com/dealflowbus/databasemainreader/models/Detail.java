package com.dealflowbus.databasemainreader.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "content"})
@Entity
@Table(name = "description")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_description")
    private int descId;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content = "";

    @OneToOne(mappedBy = "detail", fetch = FetchType.LAZY)
    private Lead lead;

    public Detail() {
    }

    public Detail(String description, String content) {
        this.description = description;
        this.content = content;
    }


    public int getDescId() {
        return descId;
    }

    public void setDescId(int descId) {
        this.descId = descId;
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
        return "Details [id=" + descId + ", description=" + description + ", content=" + content + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + descId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
        Detail other = (Detail) obj;
        return descId == other.descId;
    }
}
