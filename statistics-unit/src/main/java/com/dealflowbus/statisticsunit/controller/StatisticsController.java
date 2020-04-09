package com.dealflowbus.statisticsunit.controller;


import com.dealflowbus.statisticsunit.models.Statistics;
import com.dealflowbus.statisticsunit.statistics.MainEngine;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StatisticsController {


    private final MainEngine engine;

    public StatisticsController(MainEngine engine) {
        this.engine = engine;
    }

    @GetMapping("/stats/leads")
    @PreAuthorize("hasAuthority('read_lead')")
    public int getCount(@RequestParam(value = "count", defaultValue = "1") int countType) {

        if (countType == 1) {
            return engine.count();
        } else if (countType == 2) {
            return engine.countRejected();
        } else if (countType == 3) {
            return engine.countPortfolio();
        } else if (countType == 4) {
            return engine.countInProgress();
        } else if (countType == 5) {
            return engine.countForgotten();
        } else if (countType == 6) {
            return engine.countAddedInThisMonth();
        } else if (countType == 7) {
            return engine.countAddedInThisYear();
        } else {
            throw new RuntimeException("Wrong mapping param");
        }
    }


    @GetMapping("/stats/trends")
    @PreAuthorize("hasAuthority('read_lead')")
    public boolean getTrend() {

        return engine.tendencyRising();
    }

    @GetMapping("/stats/all")
    @PreAuthorize("hasAuthority('read_lead')")
    public Statistics getStats() {

        return engine.getStatistics();
    }
}
