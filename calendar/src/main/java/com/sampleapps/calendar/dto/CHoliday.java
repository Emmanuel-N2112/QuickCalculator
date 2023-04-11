package com.sampleapps.calendar.dto;

public class CHoliday implements Comparable<CHoliday> {

    private String id;

    private String name;

    private String type;

    private String country;

    private CDay day;

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getCountry() {

        return country;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public CDay getDay() {

        return day;
    }

    public void setDay(CDay day) {

        this.day = day;
    }

    @Override
    public int compareTo(CHoliday o) {

        return this.getDay()
                       .getDate()
                       .getDayOfYear() - o.getDay()
                       .getDate()
                       .getDayOfYear();
    }

}
