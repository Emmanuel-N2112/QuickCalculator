package com.sampleapps.calendar.util;

import com.sampleapps.calendar.dto.CDay;
import com.sampleapps.calendar.dto.CMonth;
import com.sampleapps.calendar.statics.TextColor;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class PrintOption {

    private static final String OUTPUT_FORMAT = "%10s";

    private PrintOption() {}

    public static void printMonth(CMonth cMonth, Locale locale) {

        System.out.println();

        System.out.println("MONTH: " + cMonth.getMonth()
                .getDisplayName(TextStyle.FULL, locale));
    }

    public static void printDays(CMonth cMonth, Locale locale) {

        System.out.println();

        for (int i = 0; i < 7; i++) {
            System.out.printf(OUTPUT_FORMAT, cMonth.getFirstDayOfWeek()
                                                     .plus(i)
                                                     .getDisplayName(TextStyle.SHORT, locale) + "  ");
        }
    }

    public static void printDaysOfMonth(CMonth cMonth, String countryCode, Locale locale) {

        cMonth.getWeeks()
                .forEach(cWeek -> {
                    System.out.println();
                    cWeek.getDays()
                            .forEach(cDay -> {
                                if (Boolean.FALSE.equals(cDay.getDisabled())) {
                                    String output =
                                            Boolean.TRUE.equals(cDay.getWeekend()) || Boolean.TRUE.equals(HolidayUtility.isHoliday(countryCode, cDay.getDate(), locale)) ?
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
    }

    public static void printDaysOfMonth(LocalDate goToDate, CMonth cMonth, String countryCode, Locale locale) {

        cMonth.getWeeks()
                .forEach(cWeek -> {
                    System.out.println();
                    cWeek.getDays()
                            .forEach(cDay -> {
                                if (Boolean.FALSE.equals(cDay.getDisabled())) {

                                    System.out.print(determineOutPutColor(cDay, goToDate, countryCode, locale));

                                } else {
                                    System.out.print(String.format(OUTPUT_FORMAT, " "));
                                }
                            });
                });

        System.out.println();
    }

    public static void printDottedLine(int number) {

        System.out.println();

        for (int i = 0; i < number; i++) {
            System.out.print("--");
        }
    }

    private static String determineOutPutColor(CDay cDay, LocalDate goToDate, String countryCode, Locale locale) {

        String output;

        if (Boolean.TRUE.equals(cDay.getWeekend()) || Boolean.TRUE.equals(HolidayUtility.isHoliday(countryCode,
                cDay.getDate(), locale))) {
            if (cDay.getDate()
                    .equals(goToDate)) {
                output =
                        TextColor.ANSI_GREEN + String.format(OUTPUT_FORMAT, cDay.getDayOfMonth() + " ") + TextColor.ANSI_RESET;
            } else {
                output =
                        TextColor.ANSI_BLUE + String.format(OUTPUT_FORMAT, cDay.getDayOfMonth() + " ") + TextColor.ANSI_RESET;
            }

        } else {
            if (cDay.getDate()
                    .equals(goToDate)) {
                output =
                        TextColor.ANSI_GREEN + String.format(OUTPUT_FORMAT, cDay.getDayOfMonth() + " ") + TextColor.ANSI_RESET;
            } else {
                output = String.format(OUTPUT_FORMAT, cDay.getDayOfMonth() + " ");
            }
        }

        return output;
    }

}
