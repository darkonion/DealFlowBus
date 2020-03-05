package com.dealflowbus.statisticsunit.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dealflowbus.statisticsunit.feign.LeadFeignServiceCashing;
import com.dealflowbus.statisticsunit.models.Lead;
import com.dealflowbus.statisticsunit.statistics.MainEngine;



@RestController
@RequestMapping("/stats")
public class StatisticsController {
	
	@Autowired
	private LeadFeignServiceCashing lfsc;
	
	private List<Lead> list;
	
	@GetMapping("/leads")
	private int getCount(@RequestParam(value = "count", defaultValue = "1") int countType) {
		
		list = lfsc.getLeadList();
		
		if (countType == 1) {
			return MainEngine.getCount(list);
		} else if (countType == 2) {
			return MainEngine.getCountRejected(list);
		} else if (countType == 3) {
			return MainEngine.getCountPortfolio(list);
		} else if (countType == 4) {
			return MainEngine.getCountInProgress(list);
		} else if (countType == 5) {
			return MainEngine.getCountForgotten(list);
		} else if (countType == 6) {
			return MainEngine.getCountAddedInThisMonth(list);
		} else if (countType == 7) {
			return MainEngine.getCountAddedInThisYear(list);
		} else {
			throw new RuntimeException("Wrong mapping param");
		}
		
	}
	
	
	@GetMapping("/trends")
	private boolean getTrend() {
		list = lfsc.getLeadList();
		return MainEngine.tendencyRising(list);
	}

}
