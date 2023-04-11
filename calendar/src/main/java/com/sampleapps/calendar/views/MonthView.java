package com.sampleapps.calendar.views;

import com.sampleapps.calendar.dto.CMonth;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

public class MonthView {

    private static final String OUTPUT_FORMAT = "%10s";

    private MonthView() {}

    public static void displayMonth(Scanner menuInput) {

        System.out.print("\nEnter year (> 1900): ");
        int year = menuInput.nextInt();

        System.out.print("\nEnter month (enter digit): ");
        int month = menuInput.nextInt();

        Locale locale = Locale.US;

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

        for (int i = 0; i < 35; i++) {
            System.out.print("--");
        }

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

        MenuView.displayHomeMenu(menuInput);

    }

}
