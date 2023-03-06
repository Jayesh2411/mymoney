package org.example.model;

public class Fund {

    private int currentAllocation;
    private float sipValue;
    private float allocationPercent;

    private float allocationChangeRate;

    public Fund() {
    }

    public Fund(int initialAllocation) {
        this.currentAllocation = initialAllocation;
    }

    public float getSipValue() {
        return sipValue;
    }

    public void setSipValue(float sipValue) {
        this.sipValue = sipValue;
    }

    public float getAllocationPercent() {
        return allocationPercent;
    }

    public void setAllocationPercent(float allocationPercent) {
        this.allocationPercent = allocationPercent;
    }

    public float getAllocationChangeRate() {
        return allocationChangeRate;
    }

    public void setAllocationChangeRate(float allocationChangeRate) {
        this.allocationChangeRate = allocationChangeRate;
    }

    public int getCurrentAllocation() {
        return currentAllocation;
    }

    public void setCurrentAllocation(int currentAllocation) {
        this.currentAllocation = currentAllocation;
    }
}
