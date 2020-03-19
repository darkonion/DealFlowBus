package com.dealflowbus.statisticsunit.statistics;

import java.time.LocalDate;
import java.util.List;

import com.dealflowbus.statisticsunit.models.Lead;

public class MainEngine {


	public static int getCount(List<Lead> leads) {
		
		return leads.size();
		
	}
	
	public static int getCountForgotten(List<Lead> leads) {
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

	public static int getCountRejected(List<Lead> leads) {
		int count = 0;
		
		for (Lead n : leads) {
			if (n.isRejected() ) {
				count++;
			}
		}
		
		
		return count;
	}

	public static int getCountPortfolio(List<Lead> leads) {
		int count = 0;
		
		for (Lead n : leads) {
			if (n.isInPortfolio() && !n.isRejected()) {
				count++;
			}
		}
		
		
		return count;
	}

	public static int getCountInProgress(List<Lead> leads) {
		int count = 0;
		
		for (Lead n : leads) {
			if (n.isInProgress() && !n.isInPortfolio() && !n.isRejected()) {
				count++;	
			}
		}
		return count;
	}
	
	public static int getCountAddedInThisYear(List<Lead> leads) {
		int count = 0;
		
		for (Lead n : leads) {
			if (n.getDateArrival().getYear() == LocalDate.now().getYear()) {
				count ++;
			}
		}
		return count;
	}
	
	public static int getCountAddedInThisMonth(List<Lead> leads) {
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
		if (countLastMonth > countTwoMonthsAgo) {
			return true;
		} else {
			return false;
		}
	}
	
	
}




