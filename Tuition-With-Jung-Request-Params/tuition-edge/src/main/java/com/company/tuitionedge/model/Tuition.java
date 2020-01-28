package com.company.tuitionedge.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Tuition {

    private String major;
    private boolean inState;
    private boolean undergraduate;
    private BigDecimal tuition;

    public Tuition() {
    }

    public Tuition(String major, boolean inState, boolean undergraduate, BigDecimal tuition) {
        this.major = major;
        this.inState = inState;
        this.undergraduate = undergraduate;
        this.tuition = tuition;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public boolean isInState() {
        return inState;
    }

    public void setInState(boolean inState) {
        this.inState = inState;
    }

    public boolean isUndergraduate() {
        return undergraduate;
    }

    public void setUndergraduate(boolean undergraduate) {
        this.undergraduate = undergraduate;
    }

    public BigDecimal getTuition() {
        return tuition;
    }

    public void setTuition(BigDecimal tuition) {
        this.tuition = tuition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuition tuition1 = (Tuition) o;
        return inState == tuition1.inState &&
                undergraduate == tuition1.undergraduate &&
                Objects.equals(major, tuition1.major) &&
                Objects.equals(tuition, tuition1.tuition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, inState, undergraduate, tuition);
    }

    @Override
    public String toString() {
        return "Tuition{" +
                "major='" + major + '\'' +
                ", inState=" + inState +
                ", undergraduate=" + undergraduate +
                ", tuition=" + tuition +
                '}';
    }
}
