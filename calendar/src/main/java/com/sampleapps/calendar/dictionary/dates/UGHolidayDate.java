package com.sampleapps.calendar.dictionary.dates;

import com.sampleapps.calendar.dictionary.GeneralHoliday;

import java.time.LocalDate;
import java.time.Month;

public class UGHolidayDate implements GeneralHoliday {

    private UGHolidayDate() {}

    public static LocalDate getNewYears(int year) {

        return GeneralHoliday.getNewYears(year);
    }

    public static LocalDate getValentineDay(int year) {

        return GeneralHoliday.getValentineDay(year);
    }

    public static LocalDate getChristmas(int year) {

        return GeneralHoliday.getChristmas(year);
    }

    public static LocalDate getBoxingDay(int year) {

        return GeneralHoliday.getBoxingDay(year);
    }

    public static LocalDate getLabourDay(int year) {

        return LocalDate.of(year, Month.MAY, 1);
    }

    public static LocalDate getMartyrDay(int year) {

        return LocalDate.of(year, Month.JUNE, 3);
    }

    public static LocalDate getIndependenceDay(int year) {

        return LocalDate.of(year, Month.OCTOBER, 9);
    }

    public static LocalDate getWomenDay(int year) {

        return GeneralHoliday.getWomenDay(year);
    }

    public static LocalDate getEasterDay(int year) {

        return GeneralHoliday.getEasterDay(year);
    }

    public static LocalDate getGoodFriday(int year) {

        return GeneralHoliday.getGoodFriday(year);
    }

    public static LocalDate getEasterMonday(int year) {

        return GeneralHoliday.getEasterMonday(year);
    }

}
