package com.sampleapps.calendar.views;

import com.sampleapps.calendar.dto.CMonth;
import com.sampleapps.calendar.util.HolidayUtility;
import com.sampleapps.calendar.util.PrintOption;

import java.time.Month;
import java.util.Locale;
import java.util.Scanner;

public class MonthView {

    private MonthView() {}

    public static void displayMonth(Scanner menuInput, Locale locale) {

        System.out.print("\nEnter country (UG/US): ");
        String countryCode = menuInput.next();

        System.out.print("\nEnter year (> 1900): ");
        int year = menuInput.nextInt();

        System.out.print("\nEnter month (enter digit): ");
        int month = menuInput.nextInt();

        printMonth(year, month, countryCode, locale);

        MenuView.displayHomeMenu(menuInput);
    }

    private static void printMonth(int year, int month, String countryCode, Locale locale) {
        System.out.println("\nYEAR: " + year);

        CMonth cMonth = new CMonth();
        cMonth.setYear(year);
        cMonth.setMonth(Month.of(month));
        cMonth.setLocale(locale);

        PrintOption.printMonth(cMonth, locale);

        PrintOption.printDays(cMonth, locale);

        PrintOption.printDottedLine(35);

        PrintOption.printDaysOfMonth(cMonth, countryCode, locale);

        HolidayUtility.displayHolidays(countryCode, cMonth);

    }

}
