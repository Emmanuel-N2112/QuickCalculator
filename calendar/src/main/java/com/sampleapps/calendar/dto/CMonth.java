package com.sampleapps.calendar.dto;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CMonth {

    private Integer year;

    private Month month;

    private Locale locale;

    private List<CHoliday> holidays = new ArrayList<>();

    public Integer getYear() {

        return year;
    }

    public void setYear(Integer year) {

        this.year = year;
    }

    public Month getMonth() {

        return month;
    }

    public void setMonth(Month month) {

        this.month = month;
    }

    public Locale getLocale() {

        return locale;
    }

    public void setLocale(Locale locale) {

        this.locale = locale;
    }

    public List<CWeek> getWeeks() {

        List<CWeek> weekList = new ArrayList<>();
        WeekFields weekFields = WeekFields.of(this.locale);

        LocalDate startMonthDate = LocalDate.of(this.year, this.month, 1);
        LocalDate endMonthDate = startMonthDate.with(TemporalAdjusters.lastDayOfMonth());

        LocalDate startWeekOfFirst = startMonthDate.with(WeekFields.of(locale)
                .dayOfWeek(), 1L);
        LocalDate startWeekOfLast = endMonthDate.with(WeekFields.of(locale)
                .dayOfWeek(), 1L);
        LocalDate endWeekOfLast = startWeekOfLast.plusDays(7);

        int weekOfYear = 0;

        while (startWeekOfFirst.isBefore(endWeekOfLast)) {

            CDay day = new CDay();
            day.setDate(startWeekOfFirst);
            day.setDayOfMonth(startWeekOfFirst.getDayOfMonth());
            day.setDayOfYear(startWeekOfFirst.getDayOfYear());
            day.setShortDate(startWeekOfFirst.getDayOfWeek()
                    .getDisplayName(TextStyle.SHORT, this.locale));
            day.setDay(startWeekOfFirst.getDayOfWeek()
                    .getDisplayName(TextStyle.FULL, this.locale));
            day.setDisabled(startWeekOfFirst.getMonthValue() != startMonthDate.getMonthValue());

            if (startWeekOfFirst.get(weekFields.weekOfYear()) != weekOfYear) {
                CWeek cWeek = new CWeek();
                cWeek.setWeek(day.getDate()
                        .get(weekFields.weekOfWeekBasedYear()));
                cWeek.getDays()
                        .add(day);

                weekList.add(cWeek);

                weekOfYear = startWeekOfFirst.get(weekFields.weekOfYear());

            } else {
                LocalDate finalStartWeekOfFirst = startWeekOfFirst;

                weekList.stream()
                        .filter(cWeek -> cWeek.getWeek() == finalStartWeekOfFirst.get(weekFields.weekOfWeekBasedYear()))
                        .findAny()
                        .ifPresent(cWeek -> cWeek.getDays()
                                .add(day));
            }

            startWeekOfFirst = startWeekOfFirst.plusDays(1);

        }

        return weekList;
    }

}
