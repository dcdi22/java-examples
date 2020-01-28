package com.trilogyed.tuitionservice.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.trilogyed.tuitionservice.model.BaseTuition;
import com.trilogyed.tuitionservice.model.Tuition;
import com.trilogyed.tuitionservice.repository.TuitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceLayer {

    private TuitionRepository repo;

    @Autowired
    public ServiceLayer(TuitionRepository repo) {
        this.repo = repo;
    }

    public List<String> getAllMajors() {

        List<BaseTuition> bt =  repo.findAll();

        return bt.stream()
                .map(BaseTuition::getMajor)
                .collect(Collectors.toList());
    }

    public Tuition getTuition(String major, boolean inState, boolean undergraduate) {

        BaseTuition bt = repo.getBaseTuitionByMajor(major);

        if (bt == null) {
            throw new IllegalArgumentException("No such major: " + major);
        }

        Tuition tuition = new Tuition();

        BigDecimal totalTuitionAmount = bt.getTuition();
        totalTuitionAmount.setScale(2, RoundingMode.HALF_UP);

        if (!inState) {
            totalTuitionAmount = totalTuitionAmount.multiply(new BigDecimal("1.25"));
        }

        if (!undergraduate) {
            totalTuitionAmount = totalTuitionAmount.multiply(new BigDecimal("1.5"));
        }

        tuition.setUndergraduate(undergraduate);
        tuition.setInState(inState);
        tuition.setMajor(major);
        tuition.setTuition(totalTuitionAmount);

        return tuition;
    }
}
