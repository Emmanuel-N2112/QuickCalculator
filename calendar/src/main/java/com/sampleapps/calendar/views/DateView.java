package com.sampleapps.calendar.views;

import com.sampleapps.calendar.dto.CMonth;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Logger;

public class DateView {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_GREEN = "\u001B[32m";

    private static final Logger LOGGER = Logger.getLogger(DateView.class.getName());

    private static final String OUTPUT_FORMAT = "%10s";

    private DateView() {}

    public static void goToToday(Scanner menuInput) {

        LocalDate goToDate = LocalDate.now();

        displayDate(goToDate);

        MenuView.displayHomeMenu(menuInput);

    }

    public static void viewDate(Scanner menuInput) {

        System.out.print("\nEnter date ([yyyy-MM-dd][dd/MM/yyyy]): ");
        String dateInput = menuInput.next();

        LocalDate goToDate = stringToLocalDate(dateInput);

        assert goToDate != null;
        displayDate(goToDate);

        MenuView.displayHomeMenu(menuInput);

    }

    private static void displayDate(LocalDate goToDate) {

        Locale locale = Locale.US;

        System.out.println("\nYEAR: " + goToDate.getYear());

        CMonth cMonth = new CMonth();
        cMonth.setYear(goToDate.getYear());
        cMonth.setMonth(goToDate.getMonth());
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
                                    String output = cDay.getDate()
                                            .equals(goToDate) ? ANSI_GREEN + String.format(OUTPUT_FORMAT,
                                            cDay.getDayOfMonth() + " ") + ANSI_RESET : String.format(OUTPUT_FORMAT,
                                            cDay.getDayOfMonth() + " ");

                                    System.out.print(output);

                                } else {
                                    System.out.print(String.format(OUTPUT_FORMAT, " "));
                                }
                            });
                });

        System.out.println();
    }

    private static LocalDate stringToLocalDate(String datetime) {

        final String DATE_FORMATS = "[yyyy-MM-dd][dd/MM/yyyy]";

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATS);

            return LocalDate.parse(datetime, formatter);

        } catch (Exception e) {
            LOGGER.severe(String.format("Error formatting date! %s", e.getLocalizedMessage()));
        }

        return null;

    }

}
