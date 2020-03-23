package com.dealflowbus.statisticsunit.controller;



import com.dealflowbus.statisticsunit.models.Lead;
import com.dealflowbus.statisticsunit.service.LeadFeignServiceCashing;
import com.dealflowbus.statisticsunit.statistics.MainEngine;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
public class StatisticsController {
	

	private final LeadFeignServiceCashing lfsc;
	private List<Lead> list;


	public StatisticsController(LeadFeignServiceCashing lfsc) {
		this.lfsc = lfsc;
	}

	@GetMapping("/stats/leads")
	@PreAuthorize("hasAuthority('read_lead')")
	public int getCount(@RequestParam(value = "count", defaultValue = "1") int countType) {
		System.out.println("inside getCount method");
		
		list = lfsc.getLeadList();
		
		if (countType == 1) {
			return MainEngine.count(list);
		} else if (countType == 2) {
			return MainEngine.countRejected(list);
		} else if (countType == 3) {
			return MainEngine.countPortfolio(list);
		} else if (countType == 4) {
			return MainEngine.countInProgress(list);
		} else if (countType == 5) {
			return MainEngine.countForgotten(list);
		} else if (countType == 6) {
			return MainEngine.countAddedInThisMonth(list);
		} else if (countType == 7) {
			return MainEngine.countAddedInThisYear(list);
		} else {
			throw new RuntimeException("Wrong mapping param");
		}
		
	}
	
	
	@GetMapping("/stats/trends")
	@PreAuthorize("hasAuthority('read_lead')")
	public boolean getTrend() {
		list = lfsc.getLeadList();
		return MainEngine.tendencyRising(list);
	}

}
