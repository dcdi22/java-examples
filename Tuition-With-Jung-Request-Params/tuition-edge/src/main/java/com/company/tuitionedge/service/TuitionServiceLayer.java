package com.company.tuitionedge.service;

import com.company.tuitionedge.model.FinancialAid;
import com.company.tuitionedge.model.Tuition;
import com.company.tuitionedge.model.TuitionView;
import com.company.tuitionedge.util.feign.FinancialAidClient;
import com.company.tuitionedge.util.feign.TuitionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TuitionServiceLayer {

    @Autowired
    FinancialAidClient aidClient;

    @Autowired
    TuitionClient tuitClient;


    public TuitionView getCollegeCost(String studentId, String major, boolean inState, boolean undergraduate) {

       FinancialAid finAid = aidClient.getFinancialAidByStudent(studentId);
       BigDecimal savings = finAid.getAidAmount();

        Tuition tuition = tuitClient.getTuition(major, inState, undergraduate);
        BigDecimal tuitionCost = tuition.getTuition();

        BigDecimal totalCost = tuitionCost.subtract(savings);

        /*
        private String studentId;
        private String major;
        private Boolean inState;
        private Boolean undergraduate;
        private BigDecimal financialAid;
        private BigDecimal tuition;
        private BigDecimal cost;
         */

        TuitionView tv = new TuitionView();
        tv.setStudentId(studentId);
        tv.setMajor(major);
        tv.setInState(inState);
        tv.setUndergraduate(undergraduate);
        tv.setFinancialAid(savings);
        tv.setTuition(tuitionCost);
        tv.setCost(totalCost);
        return tv;
    }

}
