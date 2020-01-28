package com.company.tuitionedge.controller;

import com.company.tuitionedge.model.TuitionView;
import com.company.tuitionedge.service.TuitionServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TuitionCalcController {

    /*
    /cost/{studentId}/{major}?inState=[true|false]&undergraduate=[true|false]
     */

    @Autowired
    private TuitionServiceLayer service;

    @GetMapping(value = "/cost/{studentId}/{major}")
    @ResponseStatus(HttpStatus.OK)
    public TuitionView getCollegeCost(@PathVariable Integer studentId, @PathVariable String major,
                                      @RequestParam(defaultValue = "true") boolean inState,
                                      @RequestParam(defaultValue = "true") boolean undergraduate) {
        return null;
    }

}
