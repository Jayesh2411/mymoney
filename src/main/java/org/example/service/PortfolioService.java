package org.example.service;

import org.example.exception.InsufficientDataException;
import org.example.model.Month;
import org.example.model.Portfolio;

public interface PortfolioService {

    void allocateFunds(int equity, int debt, int gold);

    void initiateSIP(float equity, float debt, float gold);

    void monthChangeRate(float equityAllocationChangeRate, float debtAllocationChangeRate, float goldAllocationChangeRate, Month month);

    Portfolio balanceForMonth(Month month);

    void reBalancePortfolio() throws InsufficientDataException;


}
