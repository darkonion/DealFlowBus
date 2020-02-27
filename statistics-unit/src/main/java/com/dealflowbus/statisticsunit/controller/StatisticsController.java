package com.dealflowbus.statisticsunit.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dealflowbus.statisticsunit.feign.LeadFeignClient;
import com.dealflowbus.statisticsunit.models.Lead;


@RestController
@RequestMapping("/stats")
public class StatisticsController {
	
	@Autowired
	private LeadFeignClient lfc;
	
	
	//private MainEngine engine = new MainEngine(lfc.getLeads());
	
	/*@GetMapping("/leads")
	private List<Lead> getList() {
		List<Lead> leads = new ArrayList<Lead>();
		Page<Lead> page = lfc.getLeads();
		if (page.hasContent()) {
			leads = page.getContent();
		}
		return leads;
	}*/
}
