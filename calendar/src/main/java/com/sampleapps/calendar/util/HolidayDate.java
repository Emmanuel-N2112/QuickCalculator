package com.sampleapps.calendar.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class HolidayDate {

    private static LocalDate getNewYearsEve(int year) {

        return LocalDate.of(year, 12, 31);
    }

    private static LocalDate getObservedNewYearsEve(int year) {

        return roundDate(getNewYearsEve(year));
    }

    private static LocalDate getNewYears(int year) {

        return LocalDate.of(year, 1, 1);
    }

    private static LocalDate getObservedNewYears(int year) {

        return roundDate(getNewYears(year));
    }

    private static LocalDate getValentineDay(int year) {

        return LocalDate.of(year, 2, 14);
    }

    private static LocalDate getObservedValentineDay(int year) {

        return roundDate(getValentineDay(year));
    }

    private static LocalDate getChristmas(int year) {

        return LocalDate.of(year, 12, 25);
    }

    private static LocalDate getObservedChristmas(int year) {

        return roundDate(getChristmas(year));
    }

    private static LocalDate getBoxingDay(int year) {

        return LocalDate.of(year, 12, 26);
    }

    private static LocalDate getObservedBoxingDay(int year) {

        return roundDate(getBoxingDay(year));
    }

    // Algorithm for calculating the date of Easter Sunday (Meeus/Jones/Butcher Gregorian algorithm)
    // http://en.wikipedia.org/wiki/Computus#Meeus.2FJones.2FButcher_Gregorian_algorithm
    private static LocalDate getEasterDay(int year) {

        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;

        int z = (h + l - 7 * m + 114);

        int month = z / 31;
        int day = (z % 31) + 1;

        return LocalDate.of(year, month, day);
    }

    private static LocalDate getGoodFriday(int year) {

        return getEasterDay(year).minusDays(2);
    }

    private static LocalDate getEasterMonday(int year) {

        return getEasterDay(year).plusDays(1);
    }

    private static LocalDate roundDate(LocalDate date) {

        if (date.getDayOfWeek()
                .equals(DayOfWeek.SATURDAY)) {
            return date.minusDays(1);

        } else if (date.getDayOfWeek()
                .equals(DayOfWeek.SUNDAY)) {
            return date.plusDays(1);

        } else {
            return date;
        }
    }

}
