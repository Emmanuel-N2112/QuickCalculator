package com.sampleapps.calculators.views;

import com.sampleapps.calculators.dto.NSSFCalculator;

import java.util.Scanner;

public class NSSFView {

    private static final String OUTPUT_FORMAT = "\n%s %-15.2f %s %-15.2f %s %-15.2f %s %-15.2f";

    private NSSFView() {
    }

    public static void calculateNSSF(Scanner menuInput) {

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
            case 4 -> MenuView.displayHomeMenu(menuInput);
            default -> invalidOption(menuInput);

        }

        String output = String.format(OUTPUT_FORMAT + " %s %-15.2f", "Gross Pay: ", calculator.getGrossPay(),
                "Employer Contribution: ", calculator.getEmployerContribution(), "Employee Contribution: ",
                calculator.getEmployeeContribution(), "NSSF: ", calculator.getNSSF(), "Net Pay: ",
                calculator.getNetPay());

        System.out.println(output);

        MenuView.displayHomeMenu(menuInput);
    }

    private static void invalidOption(Scanner menuInput) {

        System.out.println("Invalid option!");

        calculateNSSF(menuInput);
    }

}
