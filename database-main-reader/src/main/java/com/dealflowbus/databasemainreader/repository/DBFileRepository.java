package com.dealflowbus.databasemainreader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dealflowbus.databasemainreader.models.DBFile;



@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

}
