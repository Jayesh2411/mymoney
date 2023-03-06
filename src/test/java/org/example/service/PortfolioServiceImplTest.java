package org.example.service;

import org.example.exception.InsufficientDataException;
import org.example.model.Month;
import org.example.model.Portfolio;
import org.example.repository.PortfolioRepository;
import org.junit.Test;

import static org.junit.Assert.*;

public class PortfolioServiceImplTest {


    public static final float DELTA = 0.0000001f;
    PortfolioServiceImpl portfolioService = new PortfolioServiceImpl(new Portfolio(6000, 3000, 1000), new PortfolioRepository());


    @Test
    public void allocateFundsShouldAllocateFunds() {

        portfolioService.allocateFunds(6000, 3000, 1000);
        Portfolio expectedPortfolio = new Portfolio(6000, 3000, 1000);

        assertEquals(expectedPortfolio, portfolioService.getPortfolio());

        assertEquals(60, Math.round(portfolioService.getPortfolio().getEquityAllocationPercent()));
        assertEquals(30, Math.round(portfolioService.getPortfolio().getDebtAllocationPercent()));
        assertEquals(10, Math.round(portfolioService.getPortfolio().getGoldAllocationPercent()));
    }

    @Test
    public void allocateFundsShouldAllocateFundsWithCorrectValues() {

        portfolioService.allocateFunds(6000, 3000, 1000);
        Portfolio expectedPortfolio = new Portfolio(7000, 3000, 1000);

        assertNotEquals(expectedPortfolio, portfolioService.getPortfolio());
    }


    @Test
    public void initiateSIPShouldAddSIPWithCorrectValues() {

        portfolioService.initiateSIP(1000, 100, 50);

        assertEquals(1000, portfolioService.getPortfolio().getSipValueForEquity(), DELTA);
        assertEquals(100, portfolioService.getPortfolio().getSipValueForDebt(), DELTA);
        assertEquals(50, portfolioService.getPortfolio().getSipValueForGold(), DELTA);
    }


    @Test
    public void monthChangeRateShouldUpdatePortfolioValues() {
        portfolioService.allocateFunds(6000, 3000, 1000);
        portfolioService.monthChangeRate(10, 10, 10, Month.JANUARY);

        Portfolio expectedPortfolio = new Portfolio(6600, 3300, 1100);

        assertEquals(expectedPortfolio, portfolioService.getPortfolio());
    }

    @Test
    public void monthChangeRateShouldUpdatePortfolioValuesForFractionalValues() {
        portfolioService.allocateFunds(6000, 3000, 1000);
        portfolioService.monthChangeRate(10.5f, 10.7f, 10, Month.JANUARY);

        Portfolio expectedPortfolio = new Portfolio(6630, 3321, 1100);
        assertEquals(expectedPortfolio, portfolioService.getPortfolio());
    }

    @Test
    public void balanceForMonthWhenDataOnlyPresentForJanuary() {

        Portfolio portfolio = new Portfolio(1000, 200, 50);
        portfolioService.allocateFunds(1000, 200, 50);
        portfolioService.repository.setPortfolioForMonth(Month.JANUARY, portfolio);

        assertEquals(portfolio, portfolioService.balanceForMonth(Month.JANUARY));
    }

    @Test
    public void balanceForMonthApril() {

        Portfolio portfolio = new Portfolio(1000, 200, 50);
        Portfolio portfolioApril = new Portfolio(2000, 250, 150);
        portfolioService.allocateFunds(1000, 200, 50);
        portfolioService.repository.setPortfolioForMonth(Month.JANUARY, portfolio);
        portfolioService.repository.setPortfolioForMonth(Month.FEBRUARY, portfolio);
        portfolioService.repository.setPortfolioForMonth(Month.MARCH, portfolio);
        portfolioService.repository.setPortfolioForMonth(Month.APRIL, portfolioApril);

        assertEquals(portfolioApril, portfolioService.balanceForMonth(Month.APRIL));
    }

    @Test(expected = InsufficientDataException.class)
    public void rebalanceShouldNotAutoAllocateFundsWhenDataIsNotPresent() throws Exception {
        portfolioService.allocateFunds(1000, 200, 50);

        portfolioService.monthChangeRate(10, 5, 2, Month.JANUARY);

        portfolioService.reBalancePortfolio();

        assertEquals(1089, portfolioService.getPortfolio().getEquityAllocation(), DELTA);
        assertEquals(218, portfolioService.getPortfolio().getDebtAllocation(), DELTA);
        assertEquals(54, portfolioService.getPortfolio().getGoldAllocation(), DELTA);

    }

    @Test
    public void rebalanceShouldAutoAllocateFundsWhenDataIsThereForSixMonths() throws Exception {


        PortfolioServiceImpl portfolioService = new PortfolioServiceImpl(new Portfolio(6000, 3000, 1000), new PortfolioRepository());


        portfolioService.allocateFunds(1000, 200, 50);

        portfolioService.monthChangeRate(10, 5, 2, Month.JANUARY);
        portfolioService.monthChangeRate(10, 5, 2, Month.FEBRUARY);
        portfolioService.monthChangeRate(10, 5, 2, Month.MARCH);
        portfolioService.monthChangeRate(10, 5, 2, Month.APRIL);
        portfolioService.monthChangeRate(10, 5, 2, Month.MAY);
        portfolioService.monthChangeRate(10, 5, 2, Month.JUNE);

        portfolioService.reBalancePortfolio();

        assertEquals(1674, Math.round(portfolioService.getPortfolio().getEquityAllocation()));
        assertEquals(334, Math.round(portfolioService.getPortfolio().getDebtAllocation()));
        assertEquals(83, Math.round(portfolioService.getPortfolio().getGoldAllocation()));

    }
}