package com.dealflowbus.statisticsunit.feign;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dealflowbus.statisticsunit.models.Lead;

@FeignClient("database-main-reader")
public interface LeadFeignClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/leads")
	List<Lead> getLeads();
	
}
