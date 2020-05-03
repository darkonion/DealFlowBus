package com.dealflowbus.statisticsunit.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Statistics {

    private int countTotal;
    private int countRejected;
    private int countPortfolio;
    private int countProgress;
    private int countForgotten;
    private int countAddedThisMonth;
    private int countAddedThisYear;
    private Map<String, Long> countByField;
    private String trend;

    public Statistics() {
    }

}
