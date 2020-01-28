package com.trilogyed.finaidservice.controller;

import com.netflix.discovery.converters.Auto;
import com.trilogyed.finaidservice.model.FinancialAid;
import com.trilogyed.finaidservice.repository.FinAidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class FinAidController {

    @Autowired
    FinAidRepository repo;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<FinancialAid> getAllFinancialAid() {
        return repo.findAll();
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public FinancialAid getFinancialAidByStudent(@PathVariable String id) {
        Optional<FinancialAid> result = repo.findFinancialAidByStudentId(id);

        if (result.isPresent()) {
            return result.get();
        } else {
            FinancialAid finAid = new FinancialAid();
            finAid.setStudentId(id);
            finAid.setAidAmount(new BigDecimal("0.00"));
            return finAid;
        }
    }
}
