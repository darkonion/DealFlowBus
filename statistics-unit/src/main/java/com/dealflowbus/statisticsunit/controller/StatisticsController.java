package com.dealflowbus.statisticsunit.controller;

import com.dealflowbus.statisticsunit.models.Statistics;
import com.dealflowbus.statisticsunit.statistics.MainEngine;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StatisticsController {


    private final MainEngine engine;

    public StatisticsController(MainEngine engine) {
        this.engine = engine;
    }

    @GetMapping("/stats/all")
    @PreAuthorize("hasAuthority('read_lead')")
    public Statistics getStats() {

        return engine.getStatistics();
    }
}
