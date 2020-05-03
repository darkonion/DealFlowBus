package com.dealflowbus.statisticsunit.feignproxy;

import com.dealflowbus.statisticsunit.models.Lead;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class LeadFeignClientHystrixFallback implements LeadFeignClient {

    @Override
    public List<Lead> getLeads() {

        Lead empty = new Lead();
        empty.setDateArrival(LocalDate.now().minusYears(10));
        empty.setLastTouched(LocalDate.now().minusYears(10));
        empty.setField("DataBase error");

        return Arrays.asList(empty);
    }
}
