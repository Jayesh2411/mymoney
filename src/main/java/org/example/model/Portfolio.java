package org.example.model;

import java.util.Objects;

public class Portfolio implements Cloneable {
    private boolean isFirstMonth = true;
    private int equityAllocation;
    private int debtAllocation;
    private int goldAllocation;

    private float sipValueForEquity;
    private float sipValueForDebt;
    private float sipValueForGold;

    private float equityAllocationPercent;
    private float debtAllocationPercent;
    private float goldAllocationPercent;

    private float equityAllocationChangeRate;
    private float debtAllocationChangeRate;
    private float goldAllocationChangeRate;


    public Portfolio(int equityAllocation, int debtAllocation, int goldAllocation) {
        this.equityAllocation = equityAllocation;
        this.debtAllocation = debtAllocation;
        this.goldAllocation = goldAllocation;
    }

    public Portfolio() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portfolio portfolio = (Portfolio) o;
        return Float.compare(portfolio.equityAllocation, equityAllocation) == 0 && Float.compare(portfolio.debtAllocation, debtAllocation) == 0 && Float.compare(portfolio.goldAllocation, goldAllocation) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(equityAllocation, debtAllocation, goldAllocation);
    }

    public float fetchTotal() {
        return (this.equityAllocation + this.debtAllocation + this.goldAllocation);
    }


    public void applyRate(float equityAllocationChangeRate, float debtAllocationChangeRate, float goldAllocationChangeRate) {

        this.equityAllocationChangeRate = equityAllocationChangeRate;
        this.debtAllocationChangeRate = debtAllocationChangeRate;
        this.goldAllocationChangeRate = goldAllocationChangeRate;

        updateRates();
        if (this.isFirstMonth) this.isFirstMonth = false;
    }

    private void updateRates() {
        if (!isFirstMonth) {
            incrementSip();
        }
        this.equityAllocation = (int) (this.equityAllocation * (100 + this.equityAllocationChangeRate) / 100);
        this.debtAllocation = (int) (this.debtAllocation * (100 + this.debtAllocationChangeRate) / 100);
        this.goldAllocation = (int) (this.goldAllocation * (100 + this.goldAllocationChangeRate) / 100);

    }

    private void incrementSip() {
        this.equityAllocation = (int) (this.equityAllocation + this.getSipValueForEquity());
        this.debtAllocation = (int) (this.debtAllocation + this.getSipValueForDebt());
        this.goldAllocation = (int) (this.goldAllocation + this.getSipValueForGold());
    }


    public float getDebtAllocation() {
        return debtAllocation;
    }

    public void setDebtAllocation(int debtAllocation) {
        this.debtAllocation = debtAllocation;
    }

    public float getGoldAllocation() {
        return goldAllocation;
    }

    public void setGoldAllocation(int goldAllocation) {
        this.goldAllocation = goldAllocation;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void reBalance() {
        float subTotal = this.fetchTotal();

        this.equityAllocation = (int) ((subTotal * this.equityAllocationPercent) / 100f);
        this.debtAllocation = (int) ((subTotal * this.debtAllocationPercent) / 100f);
        this.goldAllocation = (int) ((subTotal * this.goldAllocationPercent) / 100f);
    }

    public void setInitialAllocationPercentage() {
        float subTotal = this.fetchTotal();

        this.equityAllocationPercent = (this.equityAllocation / subTotal) * 100;
        this.debtAllocationPercent = (this.debtAllocation / subTotal) * 100;
        this.goldAllocationPercent = (this.goldAllocation / subTotal) * 100;
    }

    public float getEquityAllocationPercent() {
        return equityAllocationPercent;
    }

    public float getDebtAllocationPercent() {
        return debtAllocationPercent;
    }

    public float getGoldAllocationPercent() {
        return goldAllocationPercent;
    }

    public float getEquityAllocation() {
        return equityAllocation;
    }

    public void setEquityAllocation(int equityAllocation) {
        this.equityAllocation = equityAllocation;
    }

    public float getSipValueForEquity() {
        return sipValueForEquity;
    }

    public void setSipValueForEquity(float sipValueForEquity) {
        this.sipValueForEquity = sipValueForEquity;
    }

    public float getSipValueForDebt() {
        return sipValueForDebt;
    }

    public void setSipValueForDebt(float sipValueForDebt) {
        this.sipValueForDebt = sipValueForDebt;
    }

    public float getSipValueForGold() {
        return sipValueForGold;
    }

    public void setSipValueForGold(float sipValueForGold) {
        this.sipValueForGold = sipValueForGold;
    }


    @Override
    public String toString() {

        return equityAllocation +
                " " + debtAllocation +
                " " + goldAllocation;
    }
}

