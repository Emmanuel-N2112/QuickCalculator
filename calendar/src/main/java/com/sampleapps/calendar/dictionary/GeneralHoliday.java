package com.sampleapps.calendar.dictionary;

import java.time.LocalDate;
import java.time.Month;

public interface GeneralHoliday {

    static LocalDate getNewYears(int year) {

        return LocalDate.of(year, Month.JANUARY, 1);
    }

    static LocalDate getNewYearsEve(int year) {

        return LocalDate.of(year, Month.DECEMBER, 31);
    }

    static LocalDate getValentineDay(int year) {

        return LocalDate.of(year, Month.FEBRUARY, 14);
    }

    static LocalDate getChristmas(int year) {

        return LocalDate.of(year, Month.DECEMBER, 25);
    }

    static LocalDate getBoxingDay(int year) {

        return LocalDate.of(year, Month.DECEMBER, 26);
    }

    // Algorithm for calculating the date of Easter Sunday (Meeus/Jones/Butcher Gregorian algorithm)
    // http://en.wikipedia.org/wiki/Computus#Meeus.2FJones.2FButcher_Gregorian_algorithm
    static LocalDate getEasterDay(int year) {

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

    static LocalDate getGoodFriday(int year) {

        return getEasterDay(year).minusDays(2);
    }

    static LocalDate getEasterMonday(int year) {

        return getEasterDay(year).plusDays(1);
    }

}
