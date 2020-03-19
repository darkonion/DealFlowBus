package com.dealflowbus.statisticsunit.feignproxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dealflowbus.statisticsunit.models.Lead;


@FeignClient(name = "database-main-reader", url = "${DATABASE_MAIN_READER_URI:http://localhost}:8081")
public interface LeadFeignClient {
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/leadscrude")
	List<Lead> getLeads(@RequestHeader("Authorization") String token);
	
	//@RequestMapping(method = RequestMethod.GET, value = "/api/leads/{id}/notes")
	//List<Note> getNotes(@PathVariable(value = "id") int id);
	
}
