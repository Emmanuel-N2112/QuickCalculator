package com.javaapps.calculators;

import com.javaapps.calculators.pojos.LoanCalculator;
import com.javaapps.calculators.pojos.NSSFCalculator;
import com.javaapps.calculators.pojos.TimeOption;
import com.javaapps.calculators.pojos.VATCalculator;
import com.javaapps.calculators.statics.TimeInterval;

import java.util.Scanner;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    private static final String OUTPUT_FORMAT = "\n%s %-15.2f %s %-15.2f %s %-15.2f %s %-15.2f";

    public static void main(String[] args) {

        System.out.println("********** QUICK CALCULATOR **********");

        try (Scanner menuInput = new Scanner(System.in)) {

            displayHomeMenu(menuInput);

        } catch (Exception e) {
            LOGGER.severe(e.getLocalizedMessage());
        }
    }

    private static void displayHomeMenu(Scanner menuInput) {

        String homeView = """
                          1. VAT Calculator
                          2. NSSF Calculator
                          3. Loan Calculator
                          4. Exit
                          """;

        System.out.println("\n" + homeView);

        int menuOption = menuInput.nextInt();

        switch (menuOption) {
            case 1 -> calculateVAT(menuInput);
            case 2 -> calculateNSSF(menuInput);
            case 3 -> calculateLoan(menuInput);
            case 4 -> System.out.println("********** Thank you! **********");
            default -> invalidOption(menuInput);
        }

    }

    private static void calculateVAT(Scanner menuInput) {

        VATCalculator calculator = new VATCalculator();

        String vatView = """

                         VAT Calculator
                         1. Calculate Net Amount
                         2. Calculate Gross Amount
                         3. Calculate Rate Used
                         4. Main Menu
                         """;

        System.out.println(vatView);

        int vatOption = menuInput.nextInt();

        switch (vatOption) {
            case 1 -> {
                System.out.print("\nEnter gross amount: ");
                double grossAmount = menuInput.nextDouble();
                calculator.setGrossAmount(grossAmount);

                calculator.calculateNetAmount();

            }
            case 2 -> {
                System.out.print("\nEnter net amount: ");
                double netAmount = menuInput.nextDouble();
                calculator.setNetAmount(netAmount);

                calculator.calculateGrossAmount();
            }
            case 3 -> {
                System.out.print("\nEnter gross amount: ");
                double grossAmount = menuInput.nextDouble();
                calculator.setGrossAmount(grossAmount);

                System.out.print("\nEnter net amount: ");
                double netAmount = menuInput.nextDouble();
                calculator.setNetAmount(netAmount);

                calculator.calculateRate();
            }
            case 4 -> displayHomeMenu(menuInput);
            default -> invalidOption(menuInput);

        }

        String output = String.format(OUTPUT_FORMAT, "Amount: ", calculator.getGrossAmount(), "VAT%: ",
                calculator.getRate() * 100, "VAT: ", calculator.getVat(), "Net Amount: ", calculator.getNetAmount());

        System.out.println(output);

        displayHomeMenu(menuInput);
    }

    private static void calculateNSSF(Scanner menuInput) {

        NSSFCalculator calculator = new NSSFCalculator();

        String nssfView = """
                                                    
                          NSSF Calculator
                                                    
                          Your employer deducts 5% from the employee's total gross monthly wage and adds 10% of the total gross monthly wage
                          making a total contribution of 15% for each employee.
                                                    
                          1. Calculate NSSF Deduction
                          2. Calculate Gross Pay from Employer Contribution
                          3. Calculate Gross Pay from Employee Contribution
                          4. Main Menu
                          """;

        System.out.println(nssfView);

        int nssfOption = menuInput.nextInt();

        switch (nssfOption) {
            case 1 -> {
                System.out.print("\nEnter gross pay: ");
                double grossPay = menuInput.nextDouble();
                calculator.setGrossPay(grossPay);

                calculator.calculateNSSFDeduction();

            }
            case 2 -> {
                System.out.print("\nEnter employer contribution: ");
                double employerContribution = menuInput.nextDouble();
                calculator.setEmployerContribution(employerContribution);

                calculator.calculateGrossFromEmployerContribution();
            }
            case 3 -> {
                System.out.print("\nEnter employee contribution: ");
                double employeeContribution = menuInput.nextDouble();
                calculator.setEmployeeContribution(employeeContribution);

                calculator.calculateGrossFromEmployeeContribution();
            }
            case 4 -> displayHomeMenu(menuInput);
            default -> invalidOption(menuInput);

        }

        String output = String.format(OUTPUT_FORMAT + " %s %-15.2f", "Gross Pay: ", calculator.getGrossPay(),
                "Employer Contribution: ", calculator.getEmployerContribution(), "Employee Contribution: ",
                calculator.getEmployeeContribution(), "NSSF: ", calculator.getNSSF(), "Net Pay: ",
                calculator.getNetPay());

        System.out.println(output);

        displayHomeMenu(menuInput);
    }

    private static void calculateLoan(Scanner menuInput) {

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
            case 4 -> displayHomeMenu(menuInput);
            default -> invalidOption(menuInput);

        }

        String output = String.format(OUTPUT_FORMAT, "Principal: ", calculator.getPrincipal(), "Rate per month (%): "
                , calculator.getRate(), "Term (" + calculator.getTimeOption()
                .getPeriod()
                .name() + "): ", calculator.getTimeOption()
                .getTime(), "Amount: ", calculator.getAmount());

        System.out.println(output);

        displayHomeMenu(menuInput);

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

        displayHomeMenu(menuInput);
    }

}
