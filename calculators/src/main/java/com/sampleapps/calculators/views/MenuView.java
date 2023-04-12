package com.sampleapps.calculators.views;

import java.util.Scanner;

public class MenuView {

    private MenuView() {
    }

    public static void displayHomeMenu(Scanner menuInput) {

        String homeView = """
                                          
                1. VAT Calculator
                2. NSSF Calculator
                3. Loan Calculator
                4. Exit
                """;

        System.out.println("\n" + homeView);

        int menuOption = menuInput.nextInt();

        switch (menuOption) {
            case 1 -> VATView.calculateVAT(menuInput);
            case 2 -> NSSFView.calculateNSSF(menuInput);
            case 3 -> LoanView.calculateLoan(menuInput);
            case 4 -> System.out.println("********** Thank you! **********");
            default -> invalidOption(menuInput);
        }

    }

    private static void invalidOption(Scanner menuInput) {

        System.out.println("Invalid option!");

        displayHomeMenu(menuInput);
    }

}
