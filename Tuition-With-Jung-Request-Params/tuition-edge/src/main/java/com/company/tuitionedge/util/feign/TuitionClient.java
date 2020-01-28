package com.company.tuitionedge.util.feign;

import com.company.tuitionedge.model.Tuition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "tuition-service")
public interface TuitionClient {

    @RequestMapping(value = "/majors", method = RequestMethod.GET)
    List<String> getAllMajors();

    @RequestMapping(value = "/tuition/{major}", method = RequestMethod.GET)
    Tuition getTuition(@PathVariable String major,
                              @RequestParam(defaultValue = "true") boolean inState,
                              @RequestParam(defaultValue = "true") boolean undergraduate);
}
