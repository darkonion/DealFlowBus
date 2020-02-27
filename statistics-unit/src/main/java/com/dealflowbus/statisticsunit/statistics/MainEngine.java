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
	
	
	
}
