package com.sampleapps.calendar.views;

import com.sampleapps.calendar.dictionary.UGHoliday;
import com.sampleapps.calendar.dictionary.USHoliday;
import com.sampleapps.calendar.dto.CMonth;
import com.sampleapps.calendar.util.PrintOption;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class YearView {

    private static final String OUTPUT_FORMAT = "%10s";

    private YearView() {}

    public static void displayYear(Scanner menuInput) {

        System.out.print("\nEnter country (UG/US): ");
        String countryCode = menuInput.next();

        System.out.print("\nEnter year (> 1900): ");
        int year = menuInput.nextInt();

        Locale locale = Locale.US;

        System.out.println("\nYEAR: " + year);

        Arrays.stream(Month.values())
                .forEach(month -> {
                    CMonth cMonth = new CMonth();
                    cMonth.setYear(year);
                    cMonth.setMonth(month);
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

                    PrintOption.printDottedLine(35);

                    cMonth.getWeeks()
                            .forEach(cWeek -> {
                                System.out.println();
                                cWeek.getDays()
                                        .forEach(cDay -> {
                                            if (Boolean.FALSE.equals(cDay.getDisabled())) {
                                                System.out.print(String.format(OUTPUT_FORMAT, cDay.getDayOfMonth() +
                                                                                              " "));
                                            } else {
                                                System.out.print(String.format(OUTPUT_FORMAT, " "));
                                            }
                                        });
                            });

                    System.out.println();

                });

        displayHolidays(countryCode, year, locale);

        MenuView.displayHomeMenu(menuInput);

    }

    private static void displayHolidays(String countryCode, int year, Locale locale) {

        System.out.println("\nPublic Holidays".toUpperCase());

        String output = "\n%s %30s";

        System.out.printf(output, "Date", "Name");

        PrintOption.printDottedLine(30);

        if (countryCode.equalsIgnoreCase("UG")) {
            UGHoliday ugHoliday = new UGHoliday();
            ugHoliday.setYear(year);
            ugHoliday.setLocale(locale);
            ugHoliday.populateStaticNationalHolidays()
                    .forEach(cHoliday -> {
                        System.out.printf(output, cHoliday.getDay()
                                .getDate(), cHoliday.getName());

                    });

        } else if (countryCode.equalsIgnoreCase("US")) {
            USHoliday usHoliday = new USHoliday();
            usHoliday.setYear(year);
            usHoliday.setLocale(locale);
            usHoliday.populateStaticNationalHolidays()
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
