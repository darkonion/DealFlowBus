package com.dealflowbus.databasemainreader.repository;

import com.dealflowbus.databasemainreader.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findAllByOrderByIssueDateDesc();
}
