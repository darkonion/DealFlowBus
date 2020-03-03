package com.dealflowbus.databasemainreader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dealflowbus.commons.models.Detail;




@Repository
public interface DetailRepository extends JpaRepository<Detail, Integer> {

}
