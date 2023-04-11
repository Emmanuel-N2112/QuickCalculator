package com.sampleapps.calendar.views;

import java.util.Scanner;

public class MenuView {

    private MenuView() {}

    public static void displayHomeMenu(Scanner menuInput) {

        String homeView = """                       
                          1. View Year
                          2. View Month
                          3. View today
                          4. Go to date
                          5. View Holidays
                          6. Add Holiday
                          7. Exit
                          """;

        System.out.println("\n" + homeView);

        int menuOption = menuInput.nextInt();

        switch (menuOption) {
            case 1 -> YearView.displayYear(menuInput);
            case 2 -> MonthView.displayMonth(menuInput);
            case 3 -> DateView.goToToday(menuInput);
            case 4 -> DateView.viewDate(menuInput);
            case 5 -> HolidayView.holidayMenu(menuInput);
            case 6 -> DateView.viewDate(menuInput);
            case 7 -> System.out.println("********** Thank you! **********");
            default -> invalidOption(menuInput);
        }

    }

    private static void invalidOption(Scanner menuInput) {

        System.out.println("Invalid option!");

        displayHomeMenu(menuInput);
    }

}
