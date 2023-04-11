package com.sampleapps.calendar.views;

import com.sampleapps.calendar.dto.CMonth;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class YearView {

    private static final String OUTPUT_FORMAT = "%10s";

    public static void displayYear(Scanner menuInput) {

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

                    cMonth.getFirstDayOfWeek();

                    for (int i = 0; i < 7; i++) {
                        System.out.print(String.format(OUTPUT_FORMAT, cMonth.getFirstDayOfWeek()
                                                                              .plus(i)
                                                                              .getDisplayName(TextStyle.SHORT,
                                                                                      locale) + "  "));
                    }

                    System.out.println();

                    for (int i = 0; i < 35; i++) {
                        System.out.print("--");
                    }

                    cMonth.getWeeks()
                            .forEach(cWeek -> {
                                System.out.println();
                                cWeek.getDays()
                                        .forEach(cDay -> System.out.print(String.format(OUTPUT_FORMAT,
                                                cDay.getDayOfMonth() + " ")));
                            });

                    System.out.println();

                });

        MenuView.displayHomeMenu(menuInput);

    }

}
