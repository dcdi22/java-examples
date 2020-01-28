package com.company.tuitionedge.model;

import java.math.BigDecimal;
import java.util.Objects;

public class FinancialAid {

    private String studentId;
    private BigDecimal aidAmount;

    public FinancialAid() {
    }

    public FinancialAid(String studentId, BigDecimal aidAmount) {
        this.studentId = studentId;
        this.aidAmount = aidAmount;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinancialAid that = (FinancialAid) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(aidAmount, that.aidAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, aidAmount);
    }

    @Override
    public String toString() {
        return "FinancialAid{" +
                "studentId='" + studentId + '\'' +
                ", aidAmount=" + aidAmount +
                '}';
    }
}
