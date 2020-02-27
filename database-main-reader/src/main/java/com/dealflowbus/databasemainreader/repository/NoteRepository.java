package com.dealflowbus.databasemainreader.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dealflowbus.databasemainreader.entities.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
	
	List<Note> findAllByOrderByIssueDateDesc();

}
