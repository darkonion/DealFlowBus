package com.dealflowbus.statisticsunit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dealflowbus.statisticsunit.feign.LeadFeignClient;
import com.dealflowbus.statisticsunit.statistics.MainEngine;

@RestController
@RequestMapping("/stats")
public class StatisticsController {
	
	@Autowired
	private LeadFeignClient lfc;
	
	
	//private MainEngine engine = new MainEngine(lfc.getLeads());
	
	@GetMapping("/count")
	private int getList() {
	
		return MainEngine.getCount(lfc.getLeads());
	}
}
