package com.company.tuitionedge.service;

import com.company.tuitionedge.model.FinancialAid;
import com.company.tuitionedge.model.Tuition;
import com.company.tuitionedge.model.TuitionView;
import com.company.tuitionedge.util.feign.FinancialAidClient;
import com.company.tuitionedge.util.feign.TuitionClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@SpringBootTest
class TuitionServiceLayerTest {

    @InjectMocks
    TuitionServiceLayer service;

    @Mock
    private FinancialAidClient fClient;

    @Mock
    private TuitionClient tClient;



    @BeforeEach
    void setUp() {
        setUpFinancialAidClientMock();
        setUpTuitionClientMock();
    }


    public void setUpFinancialAidClientMock() {
        /*
        private String studentId;
        private BigDecimal aidAmount;
         */

        FinancialAid faOut = new FinancialAid("1234", new BigDecimal("100.00"));

        List<FinancialAid> faList = new ArrayList<>();
        faList.add(faOut);

        doReturn(faOut).when(fClient).getFinancialAidByStudent("1234");

    }

    public void setUpTuitionClientMock() {
        /*
        private String major;
        private boolean inState;
        private boolean undergraduate;
        private BigDecimal tuition;
         */
        Tuition tOut = new Tuition("anthropology", true, true, new BigDecimal("1000.00"));

        doReturn(tOut).when(tClient).getTuition("anthropology", true, true);
    }

    @Test
    void getCollegeCost() {
        /*
        private String studentId;
        private String major;
        private Boolean inState;
        private Boolean undergraduate;
        private BigDecimal financialAid;
        private BigDecimal tuition;
        private BigDecimal cost;
         */
        TuitionView tv = new TuitionView("1234", "anthropology", true, true, new BigDecimal("100.00"), new BigDecimal("1000.00"), new BigDecimal("900.00"));

        TuitionView expected = service.getCollegeCost("1234", "anthropology", true, true);

        assertEquals(expected, tv);
    }
}