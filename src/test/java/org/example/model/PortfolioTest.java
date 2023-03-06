package org.example.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PortfolioTest {

    float delta = 0.0000000001F;

    @Test
    public void fetchTotalShouldAddAllFundsAndReturnTotal() {
        Portfolio portfolio = new Portfolio(800, 200, 100);
        assertEquals(1100, portfolio.fetchTotal(), delta);
    }

    @Test
    public void fetchTotalShouldAddAllFundsAndReturnTotalForNegativeValues() {
        Portfolio portfolio = new Portfolio(-800, 200, 100);
        assertEquals(-500, portfolio.fetchTotal(), delta);
    }

    @Test
    public void applyRate() {

        Portfolio portfolio = new Portfolio(1000, 600, 200);

        portfolio.applyRate(10, 8, 5);
        assertEquals(1100, portfolio.getEquityAllocation(), delta);
        assertEquals(648, portfolio.getDebtAllocation(), delta);
        assertEquals(209, Math.round(portfolio.getGoldAllocation()), delta);
    }

    @Test
    public void applyRateWithSipForTwoMonths() {

        Portfolio portfolio = new Portfolio(1000, 600, 200);

        portfolio.setSipValueForEquity(200);
        portfolio.setSipValueForDebt(100);
        portfolio.setSipValueForGold(50);


        portfolio.applyRate(10, 8, 5);
        assertEquals(1100, portfolio.getEquityAllocation(), delta);
        assertEquals(648, portfolio.getDebtAllocation(), delta);
        assertEquals(209, Math.round(portfolio.getGoldAllocation()), delta);


        portfolio.applyRate(10, 8, 5);
        assertEquals(1430, portfolio.getEquityAllocation(), delta);
        assertEquals(807, Math.round(portfolio.getDebtAllocation()), delta);
        assertEquals(271, Math.round(portfolio.getGoldAllocation()), delta);


    }

}