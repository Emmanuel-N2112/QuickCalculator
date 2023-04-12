package com.sampleapps.calendar.util;

import com.sampleapps.calendar.dictionary.countries.UGHoliday;
import com.sampleapps.calendar.dictionary.countries.USHoliday;
import com.sampleapps.calendar.dto.CMonth;

import java.time.LocalDate;
import java.util.Locale;

public class HolidayDisplayUtility {

    private static final String OUTPUT_FORMAT = "\n%-25s %-25s %s";

    private static final String INVALID_COUNTRY_CODE = "\nInvalid country code!";

    private HolidayDisplayUtility() {

    }

    private static void displayHeader() {

        System.out.printf(OUTPUT_FORMAT, "Date", "Name", "Type");

        PrintOption.printDottedLine(35);
    }

    public static void displayHolidays(String countryCode, int year, Locale locale) {

        displayHeader();

        if (countryCode.equalsIgnoreCase("UG")) {
            UGHoliday ugHoliday = new UGHoliday();
            ugHoliday.setYear(year);
            ugHoliday.setLocale(locale);
            ugHoliday.getPublicHolidays().forEach(cHoliday -> {
                System.out.printf(OUTPUT_FORMAT, TimeConverter.localDateToString(cHoliday.getDay()
                                                                                         .getDate(), locale), cHoliday.getName(), cHoliday.getType());

            });

        } else if (countryCode.equalsIgnoreCase("US")) {
            USHoliday usHoliday = new USHoliday();
            usHoliday.setYear(year);
            usHoliday.setLocale(locale);
            usHoliday.getPublicHolidays().forEach(cHoliday -> {
                System.out.printf(OUTPUT_FORMAT, TimeConverter.localDateToString(cHoliday.getDay()
                                                                                         .getDate(), locale), cHoliday.getName(), cHoliday.getType());

            });

        } else {
            System.out.println(INVALID_COUNTRY_CODE);
        }

        System.out.println();
    }

    public static void displayHolidays(String countryCode, CMonth month) {

        displayHeader();

        if (countryCode.equalsIgnoreCase("UG")) {
            UGHoliday ugHoliday = new UGHoliday();
            ugHoliday.setYear(month.getYear());
            ugHoliday.setLocale(month.getLocale());
            ugHoliday.getPublicHolidays()
                     .stream()
                     .filter(cHoliday -> cHoliday.getDay().getDate().getMonth().equals(month.getMonth()))
                     .forEach(cHoliday -> System.out.printf(OUTPUT_FORMAT, TimeConverter.localDateToString(cHoliday.getDay()
                                                                                                                   .getDate(), month.getLocale()), cHoliday.getName(), cHoliday.getType()));

        } else if (countryCode.equalsIgnoreCase("US")) {
            USHoliday usHoliday = new USHoliday();
            usHoliday.setYear(month.getYear());
            usHoliday.setLocale(month.getLocale());
            usHoliday.populateStaticNationalHolidays()
                     .stream()
                     .filter(cHoliday -> cHoliday.getDay().getDate().getMonth().equals(month.getMonth()))
                     .forEach(cHoliday -> System.out.printf(OUTPUT_FORMAT, TimeConverter.localDateToString(cHoliday.getDay()
                                                                                                                   .getDate(), month.getLocale()), cHoliday.getName(), cHoliday.getType()));

        } else {
            System.out.println(INVALID_COUNTRY_CODE);
        }

        System.out.println();
    }

    public static void displayObservedHolidays(String countryCode, CMonth month) {

        displayHeader();

        if (countryCode.equalsIgnoreCase("UG")) {
            UGHoliday ugHoliday = new UGHoliday();
            ugHoliday.setYear(month.getYear());
            ugHoliday.setLocale(month.getLocale());
            ugHoliday.getObservedPublicHolidays()
                     .stream()
                     .filter(cHoliday -> cHoliday.getDay().getDate().getMonth().equals(month.getMonth()))
                     .forEach(cHoliday -> System.out.printf(OUTPUT_FORMAT, TimeConverter.localDateToString(cHoliday.getDay()
                                                                                                                   .getDate(), month.getLocale()), cHoliday.getName(), cHoliday.getType()));

        } else if (countryCode.equalsIgnoreCase("US")) {
            USHoliday usHoliday = new USHoliday();
            usHoliday.setYear(month.getYear());
            usHoliday.setLocale(month.getLocale());
            usHoliday.getObservedPublicHolidays()
                     .stream()
                     .filter(cHoliday -> cHoliday.getDay().getDate().getMonth().equals(month.getMonth()))
                     .forEach(cHoliday -> System.out.printf(OUTPUT_FORMAT, TimeConverter.localDateToString(cHoliday.getDay()
                                                                                                                   .getDate(), month.getLocale()), cHoliday.getName(), cHoliday.getType()));

        } else {
            System.out.println(INVALID_COUNTRY_CODE);
        }

        System.out.println();
    }

    public static boolean isHoliday(String countryCode, LocalDate date, Locale locale) {

        if (countryCode.equalsIgnoreCase("UG")) {
            UGHoliday ugHoliday = new UGHoliday();
            ugHoliday.setYear(date.getYear());
            ugHoliday.setLocale(locale);

            return ugHoliday.getPublicHolidays()
                            .stream()
                            .anyMatch(cHoliday -> cHoliday.getDay().getDate().equals(date));

        } else if (countryCode.equalsIgnoreCase("US")) {
            USHoliday usHoliday = new USHoliday();
            usHoliday.setYear(date.getYear());
            usHoliday.setLocale(locale);

            return usHoliday.getPublicHolidays()
                            .stream()
                            .anyMatch(cHoliday -> cHoliday.getDay().getDate().equals(date));
        } else {
            System.out.println(INVALID_COUNTRY_CODE);

            return false;
        }
    }

}
