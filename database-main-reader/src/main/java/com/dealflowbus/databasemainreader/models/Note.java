package com.dealflowbus.databasemainreader.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
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
}
