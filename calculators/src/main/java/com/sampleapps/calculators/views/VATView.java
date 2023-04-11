package com.sampleapps.calculators.views;

import com.sampleapps.calculators.dto.VATCalculator;

import java.util.Scanner;

public class VATView {

    private VATView() {}

    private static final String OUTPUT_FORMAT = "\n%s %-15.2f %s %-15.2f %s %-15.2f %s %-15.2f";

    public static void calculateVAT(Scanner menuInput) {

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
            case 4 -> MenuView.displayHomeMenu(menuInput);
            default -> invalidOption(menuInput);

        }

        String output = String.format(OUTPUT_FORMAT, "Amount: ", calculator.getGrossAmount(), "VAT%: ",
                calculator.getRate() * 100, "VAT: ", calculator.getVat(), "Net Amount: ", calculator.getNetAmount());

        System.out.println(output);

        MenuView.displayHomeMenu(menuInput);
    }

    private static void invalidOption(Scanner menuInput) {

        System.out.println("Invalid option!");

        calculateVAT(menuInput);
    }
}
