package com.dealflowbus.statisticsunit.controller;



import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dealflowbus.statisticsunit.feign.LeadFeignClient;
import com.dealflowbus.statisticsunit.models.Lead;
import com.dealflowbus.statisticsunit.models.Note;
import com.dealflowbus.statisticsunit.statistics.MainEngine;


@RestController
@RequestMapping("/stats")
public class StatisticsController {
	
	@Autowired
	private LeadFeignClient lfc;
	
	private List<Lead> list;
	
	
	@GetMapping("/leads")
	private int getCount(@RequestParam(value = "count", defaultValue = "1") int countType) {
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
		} else {
			throw new RuntimeException("Wrong mapping param");
		}
		
	}
	
	@GetMapping("/notes")
	private List<Note> getNotes() {
		return lfc.getNotes(1);
		
	}
	
	
	
	@PostConstruct
	private void restoreList() {
		list = lfc.getLeads();
	}
}
