package com.trilogyed.tuitionservice.controller;

import com.trilogyed.tuitionservice.model.Tuition;
import com.trilogyed.tuitionservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class TuitionController {

    private ServiceLayer service;

    @Autowired
    public TuitionController(ServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/majors", method = RequestMethod.GET)
    public List<String> getAllMajors() {
        return service.getAllMajors();
    }

    @RequestMapping(value = "/tuition/{major}", method = RequestMethod.GET)
    public Tuition getTuition(@PathVariable String major,
                              @RequestParam(defaultValue = "true") boolean inState,
                              @RequestParam(defaultValue = "true") boolean undergraduate) {

        return service.getTuition(major, inState, undergraduate);
    }
}
