package org.example.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Portfolio implements Cloneable {
    private boolean isFirstMonth = true;
    private Map<FundType, Fund> funds;

    public Portfolio(int equityAllocation, int debtAllocation, int goldAllocation) {
        funds = new HashMap<>();
        funds.put(FundType.EQUITY, new Fund(equityAllocation));
        funds.put(FundType.DEBT, new Fund(debtAllocation));
        funds.put(FundType.GOLD, new Fund(goldAllocation));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portfolio portfolio = (Portfolio) o;
        return portfolio.getEquityFund().getCurrentAllocation() == this.getEquityFund().getCurrentAllocation() && portfolio.getDebtFund().getCurrentAllocation() == this.getDebtFund().getCurrentAllocation() && portfolio.getGoldFund().getCurrentAllocation() == this.getGoldFund().getCurrentAllocation();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEquityFund().getCurrentAllocation(), getDebtFund().getCurrentAllocation(), getGoldFund().getCurrentAllocation());
    }

    public float fetchTotal() {
        return (getEquityFund().getCurrentAllocation() + getDebtFund().getCurrentAllocation() + getGoldFund().getCurrentAllocation());
    }


    public void applyRate(float equityAllocationChangeRate, float debtAllocationChangeRate, float goldAllocationChangeRate) {

        getEquityFund().setAllocationChangeRate(equityAllocationChangeRate);
        getDebtFund().setAllocationChangeRate(debtAllocationChangeRate);
        getGoldFund().setAllocationChangeRate(goldAllocationChangeRate);

        updateRates();
        if (this.isFirstMonth) this.isFirstMonth = false;
    }

    private void updateRates() {
        if (!isFirstMonth) {
            incrementSip();
        }
        getEquityFund().setCurrentAllocation((int) (getEquityFund().getCurrentAllocation() * (100 + this.getEquityFund().getAllocationChangeRate()) / 100));
        getDebtFund().setCurrentAllocation((int) (getDebtFund().getCurrentAllocation() * (100 + this.getDebtFund().getAllocationChangeRate()) / 100));
        getGoldFund().setCurrentAllocation((int) (getGoldFund().getCurrentAllocation() * (100 + this.getGoldFund().getAllocationChangeRate()) / 100));


    }

    private void incrementSip() {
        this.getEquityFund().setCurrentAllocation((int) (this.getEquityFund().getCurrentAllocation() + this.getEquityFund().getSipValue()));
        this.getDebtFund().setCurrentAllocation((int) (this.getDebtFund().getCurrentAllocation() + this.getDebtFund().getSipValue()));
        this.getGoldFund().setCurrentAllocation((int) (this.getGoldFund().getCurrentAllocation() + this.getGoldFund().getSipValue()));
    }


    public Portfolio portfolioSnapshot() {

        return new Portfolio(
                this.getEquityFund().getCurrentAllocation(),
                this.getDebtFund().getCurrentAllocation(),
                this.getGoldFund().getCurrentAllocation());

    }

    public void reBalance() {
        float subTotal = this.fetchTotal();

        this.getEquityFund().setCurrentAllocation((int) ((subTotal * this.getEquityFund().getAllocationPercent()) / 100f));
        this.getDebtFund().setCurrentAllocation((int) ((subTotal * this.getDebtFund().getAllocationPercent()) / 100f));
        this.getGoldFund().setCurrentAllocation((int) ((subTotal * this.getGoldFund().getAllocationPercent()) / 100f));
    }

    public void setInitialAllocationPercentage() {
        float subTotal = this.fetchTotal();

        this.getEquityFund().setAllocationPercent((this.getEquityFund().getCurrentAllocation() / subTotal) * 100);
        this.getDebtFund().setAllocationPercent((this.getDebtFund().getCurrentAllocation() / subTotal) * 100);
        this.getGoldFund().setAllocationPercent((this.getGoldFund().getCurrentAllocation() / subTotal) * 100);
    }


    public Fund getEquityFund() {
        return this.funds.getOrDefault(FundType.EQUITY, new Fund());
    }

    public Fund getDebtFund() {
        return this.funds.getOrDefault(FundType.DEBT, new Fund());
    }

    public Fund getGoldFund() {
        return this.funds.getOrDefault(FundType.GOLD, new Fund());
    }

    @Override
    public String toString() {
        return getEquityFund().getCurrentAllocation() + " " + getDebtFund().getCurrentAllocation() + " " + getGoldFund().getCurrentAllocation();
    }
}

