package com.sampleapps.calendar.dto;

import java.util.ArrayList;
import java.util.List;

public class CWeek {

    private Integer week;

    private List<CDay> days = new ArrayList<>();

    public Integer getWeek() {

        return week;
    }

    public void setWeek(Integer week) {

        this.week = week;
    }

    public List<CDay> getDays() {

        return days;
    }

    public void setDays(List<CDay> days) {

        this.days = days;
    }

}
