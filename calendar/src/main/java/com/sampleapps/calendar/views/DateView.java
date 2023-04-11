package com.sampleapps.calendar.views;

import com.sampleapps.calendar.dto.CMonth;
import com.sampleapps.calendar.statics.TextColor;
import com.sampleapps.calendar.util.PrintOption;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Logger;

import static com.sampleapps.calendar.util.TimeConverter.stringToLocalDate;

public class DateView {

    private static final Logger LOGGER = Logger.getLogger(DateView.class.getName());

    private static final String OUTPUT_FORMAT = "%10s";

    private DateView() {}

    public static void goToToday(Scanner menuInput, Locale locale) {

        LocalDate goToDate = LocalDate.now();

        displayDate(goToDate, locale);

        MenuView.displayHomeMenu(menuInput);

    }

    public static void viewDate(Scanner menuInput, Locale locale) {

        System.out.print("\nEnter date ([yyyy-MM-dd][dd/MM/yyyy]): ");
        String dateInput = menuInput.next();

        LocalDate goToDate = stringToLocalDate(dateInput, locale);

        assert goToDate != null;
        displayDate(goToDate, locale);

        MenuView.displayHomeMenu(menuInput);

    }

    private static void displayDate(LocalDate goToDate, Locale locale) {

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
                                                                  .getDisplayName(TextStyle.SHORT, locale) + "  "));
        }

        PrintOption.printDottedLine(35);

        cMonth.getWeeks()
                .forEach(cWeek -> {
                    System.out.println();
                    cWeek.getDays()
                            .forEach(cDay -> {
                                if (Boolean.FALSE.equals(cDay.getDisabled())) {

                                    String output = cDay.getDate()
                                            .equals(goToDate) ?
                                            TextColor.ANSI_GREEN + String.format(OUTPUT_FORMAT,
                                                    cDay.getDayOfMonth() + " ") + TextColor.ANSI_RESET :
                                            String.format(OUTPUT_FORMAT, cDay.getDayOfMonth() + " ");

                                    System.out.print(output);

                                } else {
                                    System.out.print(String.format(OUTPUT_FORMAT, " "));
                                }
                            });
                });

        System.out.println();
    }

}
