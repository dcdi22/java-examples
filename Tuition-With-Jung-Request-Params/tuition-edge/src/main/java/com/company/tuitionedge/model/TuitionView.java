package com.company.tuitionedge.model;

import java.math.BigDecimal;
import java.util.Objects;

public class TuitionView {

    private String studentId;
    private String major;
    private Boolean inState;
    private Boolean undergraduate;
    private BigDecimal financialAid;
    private BigDecimal tuition;
    private BigDecimal cost;

    public TuitionView() {
    }

    public TuitionView(String studentId, String major, Boolean inState, Boolean undergraduate, BigDecimal financialAid, BigDecimal tuition, BigDecimal cost) {
        this.studentId = studentId;
        this.major = major;
        this.inState = inState;
        this.undergraduate = undergraduate;
        this.financialAid = financialAid;
        this.tuition = tuition;
        this.cost = cost;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Boolean getInState() {
        return inState;
    }

    public void setInState(Boolean inState) {
        this.inState = inState;
    }

    public Boolean getUndergraduate() {
        return undergraduate;
    }

    public void setUndergraduate(Boolean undergraduate) {
        this.undergraduate = undergraduate;
    }

    public BigDecimal getFinancialAid() {
        return financialAid;
    }

    public void setFinancialAid(BigDecimal financialAid) {
        this.financialAid = financialAid;
    }

    public BigDecimal getTuition() {
        return tuition;
    }

    public void setTuition(BigDecimal tuition) {
        this.tuition = tuition;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TuitionView that = (TuitionView) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(major, that.major) &&
                Objects.equals(inState, that.inState) &&
                Objects.equals(undergraduate, that.undergraduate) &&
                Objects.equals(financialAid, that.financialAid) &&
                Objects.equals(tuition, that.tuition) &&
                Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, major, inState, undergraduate, financialAid, tuition, cost);
    }

    @Override
    public String toString() {
        return "TuitionView{" +
                "studentId='" + studentId + '\'' +
                ", major='" + major + '\'' +
                ", inState=" + inState +
                ", undergraduate=" + undergraduate +
                ", financialAid=" + financialAid +
                ", tuition=" + tuition +
                ", cost=" + cost +
                '}';
    }
}
