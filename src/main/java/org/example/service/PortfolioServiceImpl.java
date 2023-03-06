package org.example.service;

import org.example.exception.InsufficientDataException;
import org.example.model.Month;
import org.example.model.Portfolio;
import org.example.repository.PortfolioRepository;

public class PortfolioServiceImpl implements PortfolioService {


    PortfolioRepository repository;
    private final Portfolio portfolio;

    public PortfolioServiceImpl(Portfolio portfolio, PortfolioRepository repository) {
        this.portfolio = portfolio;
        this.repository = repository;
    }

    @Override
    public void allocateFunds(int equity, int debt, int gold) {
        this.portfolio.setEquityAllocation(equity);
        this.portfolio.setDebtAllocation(debt);
        this.portfolio.setGoldAllocation(gold);
        this.portfolio.setInitialAllocationPercentage();
    }

    @Override
    public void initiateSIP(float equity, float debt, float gold) {
        this.portfolio.setSipValueForEquity(equity);
        this.portfolio.setSipValueForDebt(debt);
        this.portfolio.setSipValueForGold(gold);
    }

    @Override
    public void monthChangeRate(float equityAllocationChangeRate, float debtAllocationChangeRate, float goldAllocationChangeRate, Month month) {

        this.portfolio.applyRate(equityAllocationChangeRate, debtAllocationChangeRate, goldAllocationChangeRate);
        repository.setPortfolioForMonth(month, this.getPortfolio());
    }

    @Override
    public Portfolio balanceForMonth(Month month) {
        return this.repository.getPortfolioForMonth(month);
    }

    @Override
    public void reBalancePortfolio() throws InsufficientDataException {
        if (this.repository.getSize() < 6) {
            throw new InsufficientDataException("Data Insufficient");
        }

        portfolio.reBalance();
    }

    public Portfolio getPortfolio() {
        return this.portfolio;
    }


}
