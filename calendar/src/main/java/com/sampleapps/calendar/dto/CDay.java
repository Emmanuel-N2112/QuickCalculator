package com.sampleapps.calendar.dto;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class CDay {

    private LocalDate date;

    private String day;

    private String shortDate;

    private Integer dayOfMonth;

    private Integer dayOfYear;

    private Boolean isDisabled;

    public LocalDate getDate() {

        return date;
    }

    public void setDate(LocalDate date) {

        this.date = date;
    }

    public String getDay() {

        return day;
    }

    public void setDay(String day) {

        this.day = day;
    }

    public String getShortDate() {

        return shortDate;
    }

    public void setShortDate(String shortDate) {

        this.shortDate = shortDate;
    }

    public Integer getDayOfMonth() {

        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {

        this.dayOfMonth = dayOfMonth;
    }

    public Integer getDayOfYear() {

        return dayOfYear;
    }

    public void setDayOfYear(Integer dayOfYear) {

        this.dayOfYear = dayOfYear;
    }

    public Boolean getDisabled() {

        return isDisabled;
    }

    public void setDisabled(Boolean disabled) {

        isDisabled = disabled;
    }

    public static CDay createDay(LocalDate date, Locale locale) {

        CDay cDay = new CDay();
        cDay.setDate(date);
        cDay.setDayOfMonth(date.getDayOfMonth());
        cDay.setDayOfYear(date.getDayOfYear());
        cDay.setShortDate(date.getDayOfWeek()
                .getDisplayName(TextStyle.SHORT, locale));
        cDay.setDay(date.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, locale));
        cDay.setDisabled(false);

        return cDay;
    }
}
