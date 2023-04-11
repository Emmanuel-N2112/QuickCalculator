package com.sampleapps.calendar.views;

import com.sampleapps.calendar.dto.CMonth;
import com.sampleapps.calendar.statics.TextColor;
import com.sampleapps.calendar.util.PrintOption;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

import static com.sampleapps.calendar.util.HolidayUtility.displayHolidays;
import static com.sampleapps.calendar.util.HolidayUtility.isHoliday;

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
                                                                  .getDisplayName(TextStyle.SHORT, locale) + "  "));
        }

        PrintOption.printDottedLine(35);

        cMonth.getWeeks()
                .forEach(cWeek -> {
                    System.out.println();
                    cWeek.getDays()
                            .forEach(cDay -> {
                                if (Boolean.FALSE.equals(cDay.getDisabled())) {
                                    String output =
                                            Boolean.TRUE.equals(cDay.getWeekend()) || Boolean.TRUE.equals(isHoliday(countryCode, cDay.getDate(), locale)) ?
                                                    TextColor.ANSI_BLUE + String.format(OUTPUT_FORMAT,
                                                            cDay.getDayOfMonth() + " ") + TextColor.ANSI_RESET :
                                                    String.format(OUTPUT_FORMAT, cDay.getDayOfMonth() + " ");

                                    System.out.print(output);

                                } else {
                                    System.out.print(String.format(OUTPUT_FORMAT, " "));
                                }
                            });
                });

        System.out.println();

        displayHolidays(countryCode, cMonth);

        MenuView.displayHomeMenu(menuInput);

    }

}
