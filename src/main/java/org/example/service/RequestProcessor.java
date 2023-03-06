package org.example.service;

import org.example.exception.InsufficientDataException;
import org.example.model.Month;
import org.example.model.Portfolio;
import org.example.repository.PortfolioRepository;
import org.junit.Assert;

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
    public static final String INPUT_SEPERATOR = " ";

    public static void processRequest(String filePath) {

        Scanner scannedTestData = readTestData(filePath);

        Portfolio portfolio = new Portfolio();
        PortfolioRepository portfolioRepository = new PortfolioRepository();
        PortfolioServiceImpl portfolioService = new PortfolioServiceImpl(portfolio, portfolioRepository);


        while (scannedTestData.hasNext()) {
            String[] testDataLine = scannedTestData.nextLine().split(INPUT_SEPERATOR);

            switch (testDataLine[0]) {
                case ALLOCATE:
                    checkIfDataValid(testDataLine, 4);
                    portfolioService.allocateFunds(Integer.parseInt(testDataLine[1]), Integer.parseInt(testDataLine[2]), Integer.parseInt(testDataLine[3]));
                    break;

                case SIP:
                    checkIfDataValid(testDataLine, 4);
                    portfolioService.initiateSIP(Float.parseFloat(testDataLine[1]), Float.parseFloat(testDataLine[2]), Float.parseFloat(testDataLine[3]));
                    break;

                case CHANGE:
                    checkIfDataValid(testDataLine, 5);
                    portfolioService.monthChangeRate(Float.parseFloat(testDataLine[1].replace("%", "")), Float.parseFloat(testDataLine[2].replace("%", "")), Float.parseFloat(testDataLine[3].replace("%", "")), Month.valueOf(testDataLine[4]));
                    break;

                case BALANCE:
                    checkIfDataValid(testDataLine, 2);
                    System.out.println(portfolioService.balanceForMonth(Month.valueOf(testDataLine[1])).toString());
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

    private static void checkIfDataValid(String[] inputValues, int len) {
        if (inputValues.length < len) {
            System.out.println("INVALID DATA PROVIDED");
            Assert.fail();
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
