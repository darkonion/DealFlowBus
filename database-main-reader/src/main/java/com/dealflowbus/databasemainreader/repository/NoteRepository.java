package com.dealflowbus.databasemainreader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dealflowbus.databasemainreader.entities.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

}
