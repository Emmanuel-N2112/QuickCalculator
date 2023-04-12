package com.sampleapps.calendar.dictionary;

import com.sampleapps.calendar.dto.CDay;
import com.sampleapps.calendar.dto.CHoliday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.UUID;

public class HolidayDictionary {

    private Integer year;

    private Locale locale;

    public Locale getLocale() {

        return locale;
    }

    public void setLocale(Locale locale) {

        this.locale = locale;
    }

    public Integer getYear() {

        return year;
    }

    public void setYear(Integer year) {

        this.year = year;
    }

    public static LocalDate roundDate(LocalDate date) {

        if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            return date.minusDays(1);

        } else if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return date.plusDays(1);

        } else {
            return date;
        }
    }

    public static LocalDate getDynamicHoliday(int year, Month month, int occurrence, DayOfWeek dayOfWeek) {

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());

        int count = 1;

        while (startDate.isBefore(endDate)) {
            if (startDate.getDayOfWeek().equals(dayOfWeek) && count != occurrence) {
                count++;
            }

            startDate = startDate.plusDays(1);
        }

        return startDate;
    }

    public CHoliday addHoliday(String name, String type, LocalDate date) {

        CHoliday cHoliday = new CHoliday();
        cHoliday.setId(UUID.randomUUID().toString());
        cHoliday.setName(name);
        cHoliday.setType(type);
        cHoliday.setDay(CDay.createDay(date, getLocale()));

        return cHoliday;
    }

}
