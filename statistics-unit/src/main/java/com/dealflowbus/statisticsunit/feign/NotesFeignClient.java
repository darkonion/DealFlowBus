/*package com.dealflowbus.statisticsunit.feign;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dealflowbus.statisticsunit.models.Lead;
import com.dealflowbus.statisticsunit.models.Note;

import feign.Param;

@FeignClient("api-gateway")
@RibbonClient("database-main-reader")
public interface NotesFeignClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/database-main-reader/api/leads/{id}/notes")
	List<Note> getNotes(@Param(value = "id") int id);
	
}*/
