package com.dealflowbus.statisticsunit.statistics;

import com.dealflowbus.statisticsunit.models.Lead;

import java.time.LocalDate;
import java.util.List;

public class MainEngine {
	
	
	private MainEngine() {
		
	}

	public static int count(List<Lead> leads) {
		
		return leads.size();
		
	}
	
	public static int countForgotten(List<Lead> leads) {
		LocalDate monthAgo = LocalDate.now().minusMonths(1);
		int count = 0;
		
		for (Lead n : leads) {
			if (!n.isRejected() && !n.isInPortfolio() 
					&& monthAgo.isAfter(n.getLastTouched())) {
				count++;
			}
		}
		return count;
		
	}

	public static int countRejected(List<Lead> leads) {
		int count = 0;
		
		for (Lead n : leads) {
			if (n.isRejected() ) {
				count++;
			}
		}
		
		
		return count;
	}

	public static int countPortfolio(List<Lead> leads) {
		int count = 0;
		
		for (Lead n : leads) {
			if (n.isInPortfolio() && !n.isRejected()) {
				count++;
			}
		}
		
		
		return count;
	}

	public static int countInProgress(List<Lead> leads) {
		int count = 0;
		
		for (Lead n : leads) {
			if (n.isInProgress() && !n.isInPortfolio() && !n.isRejected()) {
				count++;	
			}
		}
		return count;
	}
	
	public static int countAddedInThisYear(List<Lead> leads) {
		int count = 0;
		
		for (Lead n : leads) {
			if (n.getDateArrival().getYear() == LocalDate.now().getYear()) {
				count ++;
			}
		}
		return count;
	}
	
	public static int countAddedInThisMonth(List<Lead> leads) {
		int count = 0;
		
		for (Lead n : leads) {
			if (n.getDateArrival().getMonth() == LocalDate.now().getMonth()) {
				count ++;
			}
		}
		return count;
	}
	
	public static boolean tendencyRising(List<Lead> leads) {
		int countLastMonth = 0;
		int countTwoMonthsAgo = 0;
		for (Lead n : leads) {
			if (n.getDateArrival().getMonth() == LocalDate.now().getMonth().minus(1)) {
				countLastMonth++;
			}
			if (n.getDateArrival().getMonth() == LocalDate.now().getMonth().minus(2)) {
				countTwoMonthsAgo++;
			}
	
		}
        return countLastMonth > countTwoMonthsAgo;
	}
	
	
}




