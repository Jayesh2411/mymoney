package org.example.repository;

import org.example.model.Month;
import org.example.model.Portfolio;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PortfolioRepositoryTest {


    @Test
    public void setPortfolioForMonthShouldAddValueForMonth() {
        Portfolio portfolio = new Portfolio(100, 200, 50);

        PortfolioRepository portfolioRepository = new PortfolioRepository();
        portfolioRepository.setPortfolioForMonth(Month.JANUARY, portfolio);

        assertEquals(portfolio, portfolioRepository.getPortfolioForMonth(Month.JANUARY));
    }

    @Test
    public void setPortfolioForMonthShouldAddValueForThreeMonths() {
        Portfolio portfolioJan = new Portfolio(300, 200, 50);
        Portfolio portfolioFeb = new Portfolio(200, 100, 40);
        Portfolio portfolioMar = new Portfolio(100, 80, 20);

        PortfolioRepository portfolioRepository = new PortfolioRepository();
        portfolioRepository.setPortfolioForMonth(Month.JANUARY, portfolioJan);
        portfolioRepository.setPortfolioForMonth(Month.FEBRUARY, portfolioFeb);
        portfolioRepository.setPortfolioForMonth(Month.MARCH, portfolioMar);

        assertEquals(portfolioJan, portfolioRepository.getPortfolioForMonth(Month.JANUARY));
        assertEquals(portfolioFeb, portfolioRepository.getPortfolioForMonth(Month.FEBRUARY));
        assertEquals(portfolioMar, portfolioRepository.getPortfolioForMonth(Month.MARCH));
    }

    @Test
    public void setPortfolioForMonthShouldNotModifyObjectsInRepo() {
        Portfolio portfolioJan = new Portfolio(300, 200, 50);
        Portfolio portfolioFeb = new Portfolio(200, 100, 40);
        Portfolio portfolioMar = new Portfolio(100, 80, 20);
        Portfolio portfolioMarSnapshot = portfolioMar.portfolioSnapshot();

        PortfolioRepository portfolioRepository = new PortfolioRepository();
        portfolioRepository.setPortfolioForMonth(Month.JANUARY, portfolioJan);
        portfolioRepository.setPortfolioForMonth(Month.FEBRUARY, portfolioFeb);
        portfolioRepository.setPortfolioForMonth(Month.MARCH, portfolioMar);

        portfolioMar.getGoldFund().setCurrentAllocation(30);

        assertEquals(portfolioJan, portfolioRepository.getPortfolioForMonth(Month.JANUARY));
        assertEquals(portfolioFeb, portfolioRepository.getPortfolioForMonth(Month.FEBRUARY));
        assertEquals(portfolioMarSnapshot, portfolioRepository.getPortfolioForMonth(Month.MARCH));
    }

    @Test
    public void getPortfolioForMonthShouldReturnPortfolio() {
        Portfolio portfolio = new Portfolio(100, 200, 50);

        PortfolioRepository portfolioRepository = new PortfolioRepository();
        portfolioRepository.setPortfolioForMonth(Month.JANUARY, portfolio);

        assertEquals(portfolio, portfolioRepository.getPortfolioForMonth(Month.JANUARY));

    }
}