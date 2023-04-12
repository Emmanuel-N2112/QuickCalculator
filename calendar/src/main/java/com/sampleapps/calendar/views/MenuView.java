package com.sampleapps.calendar.views;

import java.util.Locale;
import java.util.Scanner;

public class MenuView {

    private MenuView() {

    }

    public static void displayHomeMenu(Scanner menuInput) {

        Locale locale = Locale.US;

        String homeView = """                       
                1. View Year
                2. View Month
                3. View today
                4. Go to date
                5. View Holidays
                6. Exit
                """;

        System.out.println("\n" + homeView);

        int menuOption = menuInput.nextInt();

        switch (menuOption) {
            case 1 -> YearView.displayYear(menuInput, locale);
            case 2 -> MonthView.displayMonth(menuInput, locale);
            case 3 -> DateView.goToToday(menuInput, locale);
            case 4 -> DateView.viewDate(menuInput, locale);
            case 5 -> HolidayView.holidayMenu(menuInput, locale);
            case 6 -> System.out.println("********** Thank you! **********");
            default -> invalidOption(menuInput);
        }

    }

    private static void invalidOption(Scanner menuInput) {

        System.out.println("Invalid option!");

        displayHomeMenu(menuInput);
    }

}
