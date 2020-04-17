package com.dealflowbus.statisticsunit.statistics;

import com.dealflowbus.statisticsunit.models.Lead;
import com.dealflowbus.statisticsunit.models.Statistics;
import com.dealflowbus.statisticsunit.service.LeadService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class MainEngine {

    private final LeadService leadService;

    public MainEngine(LeadService lfsc) {
        this.leadService = lfsc;

    }

    public int count() {

        return leadService.getLeadList().size();
    }

    public int countForgotten() {
        LocalDate monthAgo = LocalDate.now().minusMonths(1);

        return (int) leadService.getLeadList().stream()
                .filter(l -> !l.isRejected() && !l.isInPortfolio() && monthAgo.isAfter(l.getLastTouched()))
                .count();
    }

    public int countRejected() {

        return (int) leadService.getLeadList().stream()
                .filter(l -> l.isRejected())
                .count();
    }

    public int countPortfolio() {

        return (int) leadService.getLeadList().stream()
                .filter(l -> l.isInPortfolio())
                .count();
    }

    public int countInProgress() {

        return (int) leadService.getLeadList().stream()
                .filter(l -> l.isInProgress() && !l.isInPortfolio() && !l.isRejected()).count();
    }

    public int countAddedInThisYear() {

        int thisYear = LocalDate.now().getYear();

        return (int) leadService.getLeadList().stream()
                .filter(l -> l.getDateArrival().getYear() == thisYear)
                .count();
        }

    public int countAddedInThisMonth() {

        Month currentMonth = LocalDate.now().getMonth();

        return (int) leadService.getLeadList().stream()
                .filter(l -> l.getDateArrival().getMonth() == currentMonth)
                .count();
    }

    public boolean tendencyRising() {

        Month lastMonth = LocalDate.now().getMonth().minus(1);
        Month twoMonthsAgo = LocalDate.now().getMonth().minus(2);

        int countLastMonth = (int) leadService.getLeadList().stream()
                .filter(l -> l.getDateArrival().getMonth() == lastMonth)
                .count();

        int countTwoMonthsAgo = (int) leadService.getLeadList().stream()
                .filter(l -> l.getDateArrival().getMonth() == twoMonthsAgo)
                .count();

        return countLastMonth > countTwoMonthsAgo;
    }

    public Map<String, Long> countByField() {
        List<Lead> list = leadService.getLeadList();
        Map<String, Long> countByField = list.stream()
                .filter(l -> l.getField() != null)
                .collect(Collectors.groupingBy(Lead::getField, Collectors.counting()));

        return countByField;
    }

    public Statistics getStatistics() {
        Statistics stats = new Statistics();
        stats.setCountTotal(count());
        stats.setCountProgress(countInProgress());
        stats.setCountPortfolio(countPortfolio());
        stats.setCountForgotten(countForgotten());
        stats.setCountRejected(countRejected());
        stats.setCountAddedThisMonth(countAddedInThisMonth());
        stats.setCountAddedThisYear(countAddedInThisYear());
        stats.setCountByField(countByField());

        if (tendencyRising()) {
            stats.setTrend("Rising trend");
        } else {
            stats.setTrend("Downward trend");
        }

        return stats;

    }



}




