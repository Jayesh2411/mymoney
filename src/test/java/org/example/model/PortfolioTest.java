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
        assertEquals(1100, portfolio.getEquityFund().getCurrentAllocation(), delta);
        assertEquals(648, portfolio.getDebtFund().getCurrentAllocation(), delta);
        assertEquals(210, portfolio.getGoldFund().getCurrentAllocation(), delta);
    }

    @Test
    public void applyRateWithSipForTwoMonths() {

        Portfolio portfolio = new Portfolio(1000, 600, 200);

        portfolio.getEquityFund().setSipValue(200);
        portfolio.getDebtFund().setSipValue(100);
        portfolio.getGoldFund().setSipValue(50);


        portfolio.applyRate(10, 8, 5);
        assertEquals(1100, portfolio.getEquityFund().getCurrentAllocation(), delta);
        assertEquals(648, portfolio.getDebtFund().getCurrentAllocation(), delta);
        assertEquals(210, Math.round(portfolio.getGoldFund().getCurrentAllocation()), delta);


        portfolio.applyRate(10, 8, 5);
        assertEquals(1430, portfolio.getEquityFund().getCurrentAllocation(), delta);
        assertEquals(807, Math.round(portfolio.getDebtFund().getCurrentAllocation()), delta);
        assertEquals(273, Math.round(portfolio.getGoldFund().getCurrentAllocation()), delta);


    }

}