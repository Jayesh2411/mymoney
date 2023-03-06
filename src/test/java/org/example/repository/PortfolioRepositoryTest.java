package org.example.repository;

import org.example.model.Month;
import org.example.model.Portfolio;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PortfolioRepositoryTest {


    @Test
    public void setPortfolioForMonthShouldAddValueForMonth() {
        Portfolio portfolio = new Portfolio(100, 200, 50);

        PortfolioRepository portfolioRepository = new PortfolioRepository();
        portfolioRepository.setPortfolioForMonth(Month.JANUARY, portfolio);

        assertTrue(portfolio.equals(portfolioRepository.getPortfolioForMonth(Month.JANUARY)));
    }

    @Test
    public void setPortfolioForMonthShouldAddValueForThreeMonths() {
        Portfolio portfolioJan = new Portfolio(300, 200, 50);
        Portfolio portfolioFeb = new Portfolio(200, 100, 40);
        Portfolio portfolioMar = new Portfolio(100, 80, 20);
        Portfolio expectedPortfolioMar = new Portfolio(100, 80, 20);

        PortfolioRepository portfolioRepository = new PortfolioRepository();
        portfolioRepository.setPortfolioForMonth(Month.JANUARY, portfolioJan);
        portfolioRepository.setPortfolioForMonth(Month.FEBRUARY, portfolioFeb);
        portfolioRepository.setPortfolioForMonth(Month.MARCH, portfolioMar);


        portfolioMar.setEquityAllocation(200);

        portfolioRepository.setPortfolioForMonth(Month.APRIL, portfolioMar);


        assertTrue(portfolioJan.equals(portfolioRepository.getPortfolioForMonth(Month.JANUARY)));
        assertTrue(portfolioFeb.equals(portfolioRepository.getPortfolioForMonth(Month.FEBRUARY)));
        assertTrue(expectedPortfolioMar.equals(portfolioRepository.getPortfolioForMonth(Month.MARCH)));

        assertTrue(portfolioMar.equals(portfolioRepository.getPortfolioForMonth(Month.APRIL)));
    }

    @Test
    public void getPortfolioForMonthShouldReturnPortfolio() {
        Portfolio portfolio = new Portfolio(100, 200, 50);

        PortfolioRepository portfolioRepository = new PortfolioRepository();
        portfolioRepository.setPortfolioForMonth(Month.JANUARY, portfolio);

        assertTrue(portfolio.equals(portfolioRepository.getPortfolioForMonth(Month.JANUARY)));

    }
}