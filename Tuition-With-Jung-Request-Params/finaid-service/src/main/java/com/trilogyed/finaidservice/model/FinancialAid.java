package com.trilogyed.finaidservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class FinancialAid {

    @Id
    private String studentId;
    private BigDecimal aidAmount;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public BigDecimal getAidAmount() {
        return aidAmount;
    }

    public void setAidAmount(BigDecimal aidAmount) {
        this.aidAmount = aidAmount;
    }
}
