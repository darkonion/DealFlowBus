package com.dealflowbus.statisticsunit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dealflowbus.statisticsunit.config.AccessToken;
import com.dealflowbus.statisticsunit.feignproxy.LeadFeignClient;
import com.dealflowbus.statisticsunit.models.Lead;

@Service
public class LeadFeignServiceCashing {
	
	@Autowired
	private LeadFeignClient leadFeignClient;
	
	

	@Cacheable("leads")
	public List<Lead> getLeadList() {
		System.out.println("cache is being used");
		return leadFeignClient.getLeads(AccessToken.getToken());

	}
	
}
