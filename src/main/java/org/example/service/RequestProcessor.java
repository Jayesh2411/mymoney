package org.example.service;

import org.example.exception.InsufficientDataException;
import org.example.model.Month;
import org.example.model.Portfolio;
import org.example.repository.PortfolioRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RequestProcessor {

    private static final String ALLOCATE = "ALLOCATE";
    private static final String SIP = "SIP";
    private static final String CHANGE = "CHANGE";
    private static final String BALANCE = "BALANCE";
    private static final String REBALANCE = "REBALANCE";

    public static void processRequest(String filePath) {

        Scanner testDataValues = readTestData(filePath);

        Portfolio portfolio = new Portfolio();
        PortfolioRepository portfolioRepository = new PortfolioRepository();
        PortfolioServiceImpl portfolioService = new PortfolioServiceImpl(portfolio, portfolioRepository);


        while (testDataValues.hasNext()) {
            String[] inputValues = testDataValues.nextLine().split(" ");

            switch (inputValues[0]) {
                case ALLOCATE:
                    portfolioService.allocateFunds(Integer.parseInt(inputValues[1]), Integer.parseInt(inputValues[2]), Integer.parseInt(inputValues[3]));
                    break;

                case SIP:
                    portfolioService.initiateSIP(Float.parseFloat(inputValues[1]), Float.parseFloat(inputValues[2]), Float.parseFloat(inputValues[3]));
                    break;

                case CHANGE:
                    portfolioService.monthChangeRate(Float.parseFloat(inputValues[1].replace("%", "")), Float.parseFloat(inputValues[2].replace("%", "")), Float.parseFloat(inputValues[3].replace("%", "")), Month.valueOf(inputValues[4]));
                    break;

                case BALANCE:
                    System.out.println(portfolioService.balanceForMonth(Month.valueOf(inputValues[1])).toString());
                    break;

                case REBALANCE:
                    try {
                        portfolioService.reBalancePortfolio();

                        System.out.println(portfolioService.getPortfolio().toString());
                    } catch (InsufficientDataException e) {
                        System.out.println("CANNOT_REBALANCE");
                    }

            }

        }
    }

    public static Scanner readTestData(String path) {
        File testDataFile = new File(path);
        Scanner testDataValues;
        try {
            FileInputStream fileInputStream = new FileInputStream(testDataFile);
            testDataValues = new Scanner(fileInputStream);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return testDataValues;
    }

}
