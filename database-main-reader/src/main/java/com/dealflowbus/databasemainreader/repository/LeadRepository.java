package com.dealflowbus.databasemainreader.repository;


import com.dealflowbus.databasemainreader.models.Lead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {

    // MUST HAVE FOR STATISTICS -------------------------------
    List<Lead> findAllByOrderByLastTouchedDesc();


    //FOR WEB GUI ---------------------------------------------
    Page<Lead> findAll(Pageable pageable);

    Page<Lead> findByRejectedTrue(Pageable pageable);

    Page<Lead> findByInPortfolioTrue(Pageable pageable);

    Page<Lead> findByInProgressTrueAndRejectedFalse(Pageable pageable);

    Page<Lead> findByInPortfolioFalseAndRejectedFalse(Pageable pageable);

    List<Lead> findByProjectNameIgnoreCaseContainingOrProjectOwnerIgnoreCaseContaining(String projectName,
            String projectOwner);

}
