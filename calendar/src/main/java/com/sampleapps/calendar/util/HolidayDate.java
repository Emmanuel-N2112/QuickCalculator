package com.sampleapps.calendar.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

public class HolidayDate {

    private HolidayDate() {}

    public static LocalDate getNewYearsEve(int year) {

        return LocalDate.of(year, 12, 31);
    }

    public static LocalDate getObservedNewYearsEve(int year) {

        return roundDate(getNewYearsEve(year));
    }

    public static LocalDate getNewYears(int year) {

        return LocalDate.of(year, 1, 1);
    }

    public static LocalDate getObservedNewYears(int year) {

        return roundDate(getNewYears(year));
    }

    public static LocalDate getValentineDay(int year) {

        return LocalDate.of(year, 2, 14);
    }

    public static LocalDate getObservedValentineDay(int year) {

        return roundDate(getValentineDay(year));
    }

    public static LocalDate getChristmas(int year) {

        return LocalDate.of(year, 12, 25);
    }

    public static LocalDate getObservedChristmas(int year) {

        return roundDate(getChristmas(year));
    }

    public static LocalDate getBoxingDay(int year) {

        return LocalDate.of(year, 12, 26);
    }

    public static LocalDate getObservedBoxingDay(int year) {

        return roundDate(getBoxingDay(year));
    }

    // Algorithm for calculating the date of Easter Sunday (Meeus/Jones/Butcher Gregorian algorithm)
    // http://en.wikipedia.org/wiki/Computus#Meeus.2FJones.2FButcher_Gregorian_algorithm
    public static LocalDate getEasterDay(int year) {

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

    public static LocalDate getGoodFriday(int year) {

        return getEasterDay(year).minusDays(2);
    }

    public static LocalDate getEasterMonday(int year) {

        return getEasterDay(year).plusDays(1);
    }

    public static LocalDate getDynamicHoliday(int year, Month month, int occurrence, DayOfWeek dayOfWeek) {

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());

        int count = 1;

        while (startDate.isBefore(endDate)) {
            if (startDate.getDayOfWeek()
                        .equals(dayOfWeek) && count != occurrence) {
                count++;
            }

            startDate = startDate.plusDays(1);
        }

        return startDate;
    }

    public static LocalDate getWomenDay(int year) {

        return LocalDate.of(year, 3, 8);
    }

    public static LocalDate getObservedWomenDay(int year) {

        return roundDate(getWomenDay(year));
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
