package com.company.tuitionedge.util.feign;

import com.company.tuitionedge.model.FinancialAid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "finaid-service")
public interface FinancialAidClient {

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<FinancialAid> getAllFinancialAid();

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public FinancialAid getFinancialAidByStudent(@PathVariable String id);
}
