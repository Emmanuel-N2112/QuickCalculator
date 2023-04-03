package com.javaapps.calculators;

import com.javaapps.calculators.pojos.VATCalculator;

import java.util.Scanner;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

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
            case 4 -> System.out.println("Thank you!");
            default -> invalidOption();
        }

    }

    private static void calculateVAT(Scanner menuInput) {

        VATCalculator calculator = new VATCalculator();

        String vatView = """

                         VAT Calculator
                         1. Calculate Net Amount
                         2. Calculate Gross Amount
                         3. Calculate Rate Used
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
            default -> invalidOption();

        }

        String output = String.format("%s %-15.2f %s %-15.2f %s %-15.2f %s %-15.2f", "Amount: ",
                calculator.getGrossAmount(), "VAT%: ", calculator.getRate() * 100, "VAT: ", calculator.getVat(), "Net Amount: ", calculator.getNetAmount());

        System.out.println(output);

        displayHomeMenu(menuInput);
    }

    private static void calculateNSSF(Scanner menuInput) {

    }

    private static void calculateLoan(Scanner menuInput) {

    }

    private static void invalidOption() {

        System.out.println("Invalid option!");
    }

}
