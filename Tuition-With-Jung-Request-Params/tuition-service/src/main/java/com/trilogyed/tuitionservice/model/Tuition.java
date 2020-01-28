package com.trilogyed.tuitionservice.model;

import java.math.BigDecimal;

public class Tuition {

    private String major;
    private boolean inState;
    private boolean undergraduate;
    private BigDecimal tuition;

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
}
