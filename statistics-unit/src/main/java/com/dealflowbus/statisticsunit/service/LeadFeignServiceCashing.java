package com.dealflowbus.statisticsunit.service;

import com.dealflowbus.statisticsunit.config.AccessToken;
import com.dealflowbus.statisticsunit.feignproxy.LeadFeignClient;
import com.dealflowbus.statisticsunit.models.Lead;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadFeignServiceCashing {

    private final LeadFeignClient leadFeignClient;

    public LeadFeignServiceCashing(LeadFeignClient leadFeignClient) {
        this.leadFeignClient = leadFeignClient;
    }

    @Cacheable("leads")
    public List<Lead> getLeadList() {

        return leadFeignClient.getLeads(AccessToken.getToken());
    }
}
