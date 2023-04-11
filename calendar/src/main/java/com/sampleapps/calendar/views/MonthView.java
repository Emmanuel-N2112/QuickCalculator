package com.sampleapps.calendar.views;

import com.sampleapps.calendar.dictionary.UGHoliday;
import com.sampleapps.calendar.dictionary.USHoliday;
import com.sampleapps.calendar.dto.CMonth;
import com.sampleapps.calendar.util.PrintOption;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

public class MonthView {

    private static final String OUTPUT_FORMAT = "%10s";

    private MonthView() {}

    public static void displayMonth(Scanner menuInput, Locale locale) {

        System.out.print("\nEnter country (UG/US): ");
        String countryCode = menuInput.next();

        System.out.print("\nEnter year (> 1900): ");
        int year = menuInput.nextInt();

        System.out.print("\nEnter month (enter digit): ");
        int month = menuInput.nextInt();

        System.out.println("\nYEAR: " + year);

        CMonth cMonth = new CMonth();
        cMonth.setYear(year);
        cMonth.setMonth(Month.of(month));
        cMonth.setLocale(locale);

        System.out.println("\nMONTH: " + cMonth.getMonth()
                .getDisplayName(TextStyle.FULL, locale));

        System.out.println();

        for (int i = 0; i < 7; i++) {
            System.out.print(String.format(OUTPUT_FORMAT, cMonth.getFirstDayOfWeek()
                                                                  .plus(i)
                                                                  .getDisplayName(TextStyle.SHORT,
                                                                          locale) + "  "));
        }

        System.out.println();

        PrintOption.printDottedLine(35);

        cMonth.getWeeks()
                .forEach(cWeek -> {
                    System.out.println();
                    cWeek.getDays()
                            .forEach(cDay -> {
                                if (Boolean.FALSE.equals(cDay.getDisabled())) {
                                    System.out.print(String.format(OUTPUT_FORMAT,
                                            cDay.getDayOfMonth() + " "));
                                } else {
                                    System.out.print(String.format(OUTPUT_FORMAT, " "));
                                }
                            });
                });

        System.out.println();

        displayHolidays(countryCode, cMonth);

        MenuView.displayHomeMenu(menuInput);

    }

    private static void displayHolidays(String countryCode, CMonth month) {

        System.out.println("\nPublic Holidays" .toUpperCase());

        String output = "\n%s \t \t \t%s";

        System.out.printf("%n%s \t \t \t \t%s", "Date", "Name");

        PrintOption.printDottedLine(30);

        if (countryCode.equalsIgnoreCase("UG")) {
            UGHoliday ugHoliday = new UGHoliday();
            ugHoliday.setYear(month.getYear());
            ugHoliday.setLocale(month.getLocale());
            ugHoliday.getPublicHolidays()
                    .stream()
                    .filter(cHoliday -> cHoliday.getDay()
                            .getDate()
                            .getMonth()
                            .equals(month.getMonth()))
                    .forEach(cHoliday -> {
                        System.out.printf(output, cHoliday.getDay()
                                .getDate(), cHoliday.getName());

                    });

        } else if (countryCode.equalsIgnoreCase("US")) {
            USHoliday usHoliday = new USHoliday();
            usHoliday.setYear(month.getYear());
            usHoliday.setLocale(month.getLocale());
            usHoliday.populateStaticNationalHolidays()
                    .stream()
                    .filter(cHoliday -> cHoliday.getDay()
                            .getDate()
                            .getMonth()
                            .equals(month.getMonth()))
                    .forEach(cHoliday -> {
                        System.out.printf(output, cHoliday.getDay()
                                .getDate(), cHoliday.getName());

                    });

        } else {
            System.out.println("\nInvalid country code!");
        }

        System.out.println();
    }

}
