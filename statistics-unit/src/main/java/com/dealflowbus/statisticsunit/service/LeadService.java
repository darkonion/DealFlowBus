package com.dealflowbus.statisticsunit.service;

import com.dealflowbus.statisticsunit.feignproxy.LeadFeignClient;
import com.dealflowbus.statisticsunit.models.Lead;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadService {

    private final LeadFeignClient leadFeignClient;

    public LeadService(LeadFeignClient leadFeignClient) {
        this.leadFeignClient = leadFeignClient;
    }

    @Cacheable("leads")
    public List<Lead> getLeadList() {

        return leadFeignClient.getLeads();
    }
}
