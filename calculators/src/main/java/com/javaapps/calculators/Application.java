package com.javaapps.calculators;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        System.out.println("********** QUICK CALCULATOR **********");
        displayHomeMenu();
    }

    private static void displayHomeMenu() {
        String homeView = """
            1. VAT Calculator
            2. NSSF Calculator
            3. Loan Calculator
            4. Exit
            """;

        System.out.println("\n" + homeView);

        Scanner menuInput = new Scanner(System.in);
        int menuOption = menuInput.nextInt();
        menuInput.close();

        switch (menuOption) {
            case 1 -> calculateVAT();
            case 2 -> calculateNSSF();
            case 3 -> calculateLoan();
            case 4 -> System.out.println("Thank you!");
            default -> throw new IllegalArgumentException("Unexpected value: " + menuOption);
        }
        
    }

    private static void calculateVAT() {
        VATCalculator calculator = new VATCalculator();

        String vatView = """

            VAT Calculator
            1. Calculate Net Amount
            2. Calculate Gross Amount
            3. Calculate Rate Used
            """;

        System.out.println(vatView);

        Scanner menuInput = new Scanner(System.in);
        int vatOption = menuInput.nextInt();
        menuInput.close();

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
            default -> throw new IllegalArgumentException("Unexpected VAT option: " + vatOption);
        }

        String output = String.format("%s %-15.2f %s %-15.2f %s %-15.2f %s %-15.2f", "Amount: ", calculator
            .getGrossAmount(), "VAT%: ", calculator.getRate() * 100,
            "VAT: ", calculator.getVat(), "Net Amount: ", calculator.getNetAmount());

        System.out.println(output);

        displayHomeMenu();
    }

    private static void calculateNSSF() {

    }

    private static void calculateLoan() {

    }

}
