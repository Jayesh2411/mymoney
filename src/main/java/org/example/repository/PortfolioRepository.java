package org.example.repository;

import org.example.model.Month;
import org.example.model.Portfolio;

import java.util.HashMap;
import java.util.Map;

public class PortfolioRepository {

    public static final Portfolio DEFAULT_VALUE = new Portfolio(0, 0, 0);
    private final Map<Month, Portfolio> portfolioMap = new HashMap<>();

    public PortfolioRepository() {
    }

    public void setPortfolioForMonth(Month month, Portfolio portfolio) {
        Portfolio portfolioCopy = null;
        try {
            portfolioCopy = (Portfolio) portfolio.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        } finally {
            this.portfolioMap.put(month, portfolioCopy);
        }

    }

    public Portfolio getPortfolioForMonth(Month month) {
        return this.portfolioMap.getOrDefault(month, DEFAULT_VALUE);
    }

    public int getSize() {
        return this.portfolioMap.size();
    }
}
