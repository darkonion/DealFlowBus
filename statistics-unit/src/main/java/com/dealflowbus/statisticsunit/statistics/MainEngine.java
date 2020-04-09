package com.dealflowbus.statisticsunit.statistics;

import com.dealflowbus.statisticsunit.models.Lead;
import com.dealflowbus.statisticsunit.models.Statistics;
import com.dealflowbus.statisticsunit.service.LeadFeignServiceCashing;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MainEngine {

    private final LeadFeignServiceCashing lfsc;

    public MainEngine(LeadFeignServiceCashing lfsc) {
        this.lfsc = lfsc;

    }

    public int count() {

        return lfsc.getLeadList().size();
    }

    public int countForgotten() {
        LocalDate monthAgo = LocalDate.now().minusMonths(1);
        int count = 0;

        for (Lead n : lfsc.getLeadList()) {
            if (!n.isRejected() && !n.isInPortfolio()
                    && monthAgo.isAfter(n.getLastTouched())) {
                count++;
            }
        }
        return count;
    }

    public int countRejected() {
        int count = 0;

        for (Lead n : lfsc.getLeadList()) {
            if (n.isRejected()) {
                count++;
            }
        }


        return count;
    }

    public int countPortfolio() {
        int count = 0;

        for (Lead n : lfsc.getLeadList()) {
            if (n.isInPortfolio() && !n.isRejected()) {
                count++;
            }
        }


        return count;
    }

    public int countInProgress() {
        int count = 0;

        for (Lead n : lfsc.getLeadList()) {
            if (n.isInProgress() && !n.isInPortfolio() && !n.isRejected()) {
                count++;
            }
        }
        return count;
    }

    public int countAddedInThisYear() {
        int count = 0;

        for (Lead n : lfsc.getLeadList()) {
            if (n.getDateArrival().getYear() == LocalDate.now().getYear()) {
                count++;
            }
        }
        return count;
    }

    public int countAddedInThisMonth() {
        int count = 0;

        for (Lead n : lfsc.getLeadList()) {
            if (n.getDateArrival().getMonth() == LocalDate.now().getMonth()) {
                count++;
            }
        }
        return count;
    }

    public boolean tendencyRising() {
        int countLastMonth = 0;
        int countTwoMonthsAgo = 0;
        for (Lead n : lfsc.getLeadList()) {
            if (n.getDateArrival().getMonth() == LocalDate.now().getMonth().minus(1)) {
                countLastMonth++;
            }
            if (n.getDateArrival().getMonth() == LocalDate.now().getMonth().minus(2)) {
                countTwoMonthsAgo++;
            }
        }
        return countLastMonth > countTwoMonthsAgo;
    }

    public Map<String, Long> countByField() {
        List<Lead> list = lfsc.getLeadList();
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




