package com.sampleapps.calendar.dictionary;

import com.sampleapps.calendar.dto.CDay;
import com.sampleapps.calendar.dto.CHoliday;
import com.sampleapps.calendar.statics.HolidayNames;
import com.sampleapps.calendar.util.HolidayDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class UGHoliday {

    private static final String NAME = "UG";

    private static final String NATIONAL = "National Holiday";

    private Integer year;

    private Locale locale;

    public String getName() {

        return NAME;
    }

    public List<CHoliday> populateStaticNationalHolidays() {

        List<CHoliday> holidays = new ArrayList<>();
        holidays.add(addHoliday(HolidayNames.NEW_YEAR, HolidayDate.getNewYears(this.year)));
        holidays.add(addHoliday(HolidayNames.VALENTINE, HolidayDate.getValentineDay(this.year)));
        holidays.add(addHoliday(HolidayNames.GOOD_FRIDAY, HolidayDate.getGoodFriday(this.year)));
        holidays.add(addHoliday(HolidayNames.EASTER, HolidayDate.getEasterDay(this.year)));
        holidays.add(addHoliday(HolidayNames.EASTER_MONDAY, HolidayDate.getEasterMonday(this.year)));
        holidays.add(addHoliday(HolidayNames.CHRISTMAS, HolidayDate.getChristmas(this.year)));
        holidays.add(addHoliday(HolidayNames.BOXING, HolidayDate.getBoxingDay(this.year)));
        holidays.add(addHoliday(HolidayNames.NEW_YEAR_EVE, HolidayDate.getNewYearsEve(this.year)));

        return holidays;

    }

    public List<CHoliday> populateDynamicNationalHolidays() {

        List<CHoliday> holidays = new ArrayList<>();
        // Mother's Day - 2nd Monday of every May
        holidays.add(addHoliday(HolidayNames.MOTHER_DAY, HolidayDate.getDynamicHoliday(this.year, Month.MAY
                , 2, DayOfWeek.MONDAY)));

        // Father's Day:	3rd Sunday in June
        holidays.add(addHoliday(HolidayNames.FATHER_DAY, HolidayDate.getDynamicHoliday(this.year,
                Month.JUNE, 3, DayOfWeek.SUNDAY)));

        // Labor Day:	1st Monday in September
        holidays.add(addHoliday(HolidayNames.LABOUR, HolidayDate.getDynamicHoliday(this.year,
                Month.SEPTEMBER, 1, DayOfWeek.MONDAY)));

        return holidays;

    }

    private CHoliday addHoliday(String name, LocalDate date) {

        CHoliday cHoliday = new CHoliday();
        cHoliday.setId(UUID.randomUUID()
                .toString());
        cHoliday.setName(name);
        cHoliday.setType(UGHoliday.NATIONAL);
        cHoliday.setDay(CDay.createDay(date, this.locale));

        return cHoliday;
    }

    public Integer getYear() {

        return year;
    }

    public void setYear(Integer year) {

        this.year = year;
    }

    public void setLocale(Locale locale) {

        this.locale = locale;
    }

}
