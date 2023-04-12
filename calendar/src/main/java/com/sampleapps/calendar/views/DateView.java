package com.sampleapps.calendar.views;

import com.sampleapps.calendar.dto.CMonth;
import com.sampleapps.calendar.util.HolidayUtility;
import com.sampleapps.calendar.util.PrintOption;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

import static com.sampleapps.calendar.util.TimeConverter.stringToLocalDate;

public class DateView {

    private DateView() {}

    public static void goToToday(Scanner menuInput, Locale locale) {

        System.out.print("\nEnter country (UG/US): ");
        String countryCode = menuInput.next();

        LocalDate goToDate = LocalDate.now();

        displayDate(goToDate, locale, countryCode);

        MenuView.displayHomeMenu(menuInput);

    }

    public static void viewDate(Scanner menuInput, Locale locale) {

        System.out.print("\nEnter country (UG/US): ");
        String countryCode = menuInput.next();

        System.out.print("\nEnter date ([yyyy-MM-dd][dd/MM/yyyy]): ");
        String dateInput = menuInput.next();

        LocalDate goToDate = stringToLocalDate(dateInput, locale);

        if (goToDate != null) {
            displayDate(goToDate, locale, countryCode);

        } else {
            System.out.println("Invalid date!");

        }

        MenuView.displayHomeMenu(menuInput);

    }

    private static void displayDate(LocalDate goToDate, Locale locale, String countryCode) {

        System.out.println("\nYEAR: " + goToDate.getYear());

        CMonth cMonth = new CMonth();
        cMonth.setYear(goToDate.getYear());
        cMonth.setMonth(goToDate.getMonth());
        cMonth.setLocale(locale);

        PrintOption.printMonth(cMonth, locale);

        PrintOption.printDays(cMonth, locale);

        PrintOption.printDottedLine(35);

        PrintOption.printDaysOfMonth(goToDate, cMonth, countryCode, locale);

        HolidayUtility.displayHolidays(countryCode, cMonth);

    }

}
