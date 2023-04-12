package com.sampleapps.calendar.views;

import com.sampleapps.calendar.dto.CMonth;
import com.sampleapps.calendar.util.HolidayDisplayUtility;
import com.sampleapps.calendar.util.PrintOption;

import java.time.Month;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Logger;

public class HolidayView {

    private HolidayView() {

    }

    public static void holidayMenu(Scanner menuInput, Locale locale) {

        String holidayView = """
                                          
                Select Holiday Rule
                1. The holiday falls on the same month and date each year.
                2. If the actual holiday falls on a Saturday, provide a day off on the preceding Friday; if the holiday falls on a Sunday, then provide a day off on the Monday after the holiday.
                3. Combine both
                                          
                """;

        System.out.println(holidayView);

        int holidayOption = menuInput.nextInt();

        System.out.print("\nEnter country (UG/US): ");
        String countryCode = menuInput.next();

        System.out.print("\nEnter year (> 1900): ");
        int year = menuInput.nextInt();

        System.out.println("\nYEAR: " + year);

        if (holidayOption == 1) {
            printHolidays(year, locale, countryCode);

        } else if (holidayOption == 2) {
            printObservedHolidays(year, locale, countryCode);

        } else if (holidayOption == 3) {
            printHolidays(year, locale, countryCode);
            printObservedHolidays(year, locale, countryCode);

        } else {
            System.out.println("\nInvalid Option!");
        }

        MenuView.displayHomeMenu(menuInput);

    }

    private static void printHolidays(int year, Locale locale, String countryCode) {
        Arrays.stream(Month.values()).forEach(month -> {
            CMonth cMonth = new CMonth();
            cMonth.setYear(year);
            cMonth.setMonth(month);
            cMonth.setLocale(locale);

            PrintOption.printMonth(cMonth, locale);

            HolidayDisplayUtility.displayHolidays(countryCode, cMonth);

        });
    }

    private static void printObservedHolidays(int year, Locale locale, String countryCode) {
        Arrays.stream(Month.values()).forEach(month -> {
            CMonth cMonth = new CMonth();
            cMonth.setYear(year);
            cMonth.setMonth(month);
            cMonth.setLocale(locale);

            PrintOption.printMonth(cMonth, locale);

            HolidayDisplayUtility.displayObservedHolidays(countryCode, cMonth);

        });
    }

}
