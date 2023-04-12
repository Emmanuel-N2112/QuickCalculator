package com.sampleapps.calendar.views;

import com.sampleapps.calendar.dto.CMonth;
import com.sampleapps.calendar.util.HolidayDisplayUtility;
import com.sampleapps.calendar.util.PrintOption;

import java.time.Month;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class YearView {

    private YearView() {

    }

    public static void displayYear(Scanner menuInput, Locale locale) {

        System.out.print("\nEnter country (UG/US): ");
        String countryCode = menuInput.next();

        System.out.print("\nEnter year (> 1900): ");
        int year = menuInput.nextInt();

        printYear(year, countryCode, locale);

        MenuView.displayHomeMenu(menuInput);

    }

    private static void printYear(int year, String countryCode, Locale locale) {

        System.out.println("\nYEAR: " + year);

        Arrays.stream(Month.values())
                .forEach(month -> {
                    CMonth cMonth = new CMonth();
                    cMonth.setYear(year);
                    cMonth.setMonth(month);
                    cMonth.setLocale(locale);

                    PrintOption.printMonth(cMonth, locale);

                    PrintOption.printDays(cMonth, locale);

                    PrintOption.printDottedLine(35);

                    PrintOption.printDaysOfMonth(cMonth, countryCode, locale);

                });

        System.out.println("Holidays");

        HolidayDisplayUtility.displayHolidays(countryCode, year, locale);
    }

}
