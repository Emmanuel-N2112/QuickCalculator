package com.sampleapps.calculators.vews;

import com.sampleapps.calculators.LoanSchedule;
import com.sampleapps.calculators.pojos.LoanCalculator;
import com.sampleapps.calculators.pojos.TimeOption;
import com.sampleapps.calculators.statics.TimeInterval;

import java.util.Scanner;

public class LoanView {

    private LoanView() {}

    private static final String OUTPUT_FORMAT = "\n%s %-15.2f %s %-15.2f %s %-15.2f %s %-15.2f";

    public static void calculateLoan(Scanner menuInput) {

        LoanCalculator calculator = new LoanCalculator();

        String loanView = """
                                                    
                          Loan Calculator
                          1. Calculate Monthly Payments on a Loan (Fixed)
                          2. Determine Rate from given Amount
                          3. Determine Principal
                          4. Main Menu
                          """;

        System.out.println(loanView);

        int loanOption = menuInput.nextInt();

        switch (loanOption) {
            case 1 -> {
                System.out.print("\nEnter principal: ");
                double amountBorrowed = menuInput.nextDouble();
                calculator.setPrincipal(amountBorrowed);

                System.out.print("\nEnter rate per month (%): ");
                double rate = menuInput.nextDouble();
                calculator.setRate(rate);

                calculator.setTimeOption(getLoanTime(menuInput));

                calculator.calculateLoanAmount();

            }
            case 2 -> {
                System.out.print("\nEnter principal: ");
                double amountBorrowed = menuInput.nextDouble();
                calculator.setPrincipal(amountBorrowed);

                calculator.setTimeOption(getLoanTime(menuInput));

                System.out.print("\nEnter amount: ");
                double amount = menuInput.nextDouble();
                calculator.setAmount(amount);

                calculator.calculateRate();

            }
            case 3 -> {
                System.out.print("\nEnter rate per month (%): ");
                double rate = menuInput.nextDouble();
                calculator.setRate(rate);

                calculator.setTimeOption(getLoanTime(menuInput));

                System.out.print("\nEnter amount: ");
                double amount = menuInput.nextDouble();
                calculator.setAmount(amount);

                calculator.calculatePrincipal();

            }
            case 4 -> MenuView.displayHomeMenu(menuInput);
            default -> invalidOption(menuInput);

        }

        String output = String.format(OUTPUT_FORMAT, "Principal: ", calculator.getPrincipal(), "Rate per month (%): "
                , calculator.getRate(), "Term (" + calculator.getTimeOption()
                .getPeriod()
                .name() + "): ", calculator.getTimeOption()
                .getTime(), "Amount: ", calculator.getAmount());

        System.out.println(output);

        LoanSchedule.getLoanSchedule(menuInput, calculator);

        MenuView.displayHomeMenu(menuInput);

    }

    private static TimeOption getLoanTime(Scanner menuInput) {

        TimeOption timeOption = new TimeOption();

        String interval = """
                                                    
                          Choose time interval:
                          1. Yearly
                          2. Monthly
                          3. Weekly
                          4. Daily
                                                    
                          """;

        System.out.print(interval);

        int intervalOption = menuInput.nextInt();

        switch (intervalOption) {
            case 1 -> timeOption.setPeriod(TimeInterval.YEARLY);
            case 2 -> timeOption.setPeriod(TimeInterval.MONTHLY);
            case 3 -> timeOption.setPeriod(TimeInterval.WEEKLY);
            case 4 -> timeOption.setPeriod(TimeInterval.DAILY);
            default -> invalidOption(menuInput);
        }

        System.out.print("\nEnter time (" + timeOption.getPeriod()
                .name() + "): ");
        double time = menuInput.nextDouble();
        timeOption.setTime(time);

        return timeOption;
    }

    private static void invalidOption(Scanner menuInput) {

        System.out.println("Invalid option!");

        calculateLoan(menuInput);
    }

}
