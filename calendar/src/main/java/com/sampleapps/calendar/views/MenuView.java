package com.sampleapps.calendar.views;

import java.util.Scanner;

public class MenuView {

    private MenuView() {}

    public static void displayHomeMenu(Scanner menuInput) {

        String homeView = """                       
                          1. View Year
                          2. View Month
                          3. Public Holiday
                          4. Exit
                          """;

        System.out.println("\n" + homeView);

        int menuOption = menuInput.nextInt();

        switch (menuOption) {
            case 1 -> YearView.displayYear(menuInput);
            case 2 -> System.out.println("********** Thank you! **********");
            case 3 -> System.out.println("********** Thank you! **********");
            case 4 -> System.out.println("********** Thank you! **********");
            default -> invalidOption(menuInput);
        }

    }

    private static void invalidOption(Scanner menuInput) {

        System.out.println("Invalid option!");

        displayHomeMenu(menuInput);
    }

}
