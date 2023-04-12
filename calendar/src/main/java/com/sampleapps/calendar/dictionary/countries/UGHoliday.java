package com.sampleapps.calendar.dictionary.countries;

import com.sampleapps.calendar.dictionary.HolidayDictionary;
import com.sampleapps.calendar.dictionary.dates.UGHolidayDate;
import com.sampleapps.calendar.dto.CDay;
import com.sampleapps.calendar.dto.CHoliday;
import com.sampleapps.calendar.statics.HolidayNames;
import com.sampleapps.calendar.statics.HolidayTypes;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UGHoliday extends HolidayDictionary {

    private static final String NAME = "UG";

    public String getName() {

        return NAME;
    }

    public List<CHoliday> getPublicHolidays() {

        List<CHoliday> holidays = new ArrayList<>();
        holidays.addAll(populateStaticNationalHolidays());
        holidays.addAll(populateDynamicNationalHolidays());

        Collections.sort(holidays);

        return holidays;
    }

    protected List<CHoliday> populateStaticNationalHolidays() {

        List<CHoliday> holidays = new ArrayList<>();
        holidays.add(addHoliday(HolidayNames.NEW_YEAR, HolidayTypes.NATIONAL, UGHolidayDate.getNewYears(getYear())));
        holidays.add(addHoliday(HolidayNames.VALENTINE, HolidayTypes.NATIONAL,
                UGHolidayDate.getValentineDay(getYear())));
        holidays.add(addHoliday(HolidayNames.WOMEN_DAY, HolidayTypes.NATIONAL, UGHolidayDate.getWomenDay(getYear())));
        holidays.add(addHoliday(HolidayNames.GOOD_FRIDAY, HolidayTypes.NATIONAL,
                UGHolidayDate.getGoodFriday(getYear())));
        holidays.add(addHoliday(HolidayNames.EASTER, HolidayTypes.NATIONAL, UGHolidayDate.getEasterDay(getYear())));
        holidays.add(addHoliday(HolidayNames.EASTER_MONDAY, HolidayTypes.NATIONAL,
                UGHolidayDate.getEasterMonday(getYear())));
        holidays.add(addHoliday(HolidayNames.LABOUR, HolidayTypes.NATIONAL, UGHolidayDate.getLabourDay(getYear())));
        holidays.add(addHoliday(HolidayNames.HEROES, HolidayTypes.NATIONAL, UGHolidayDate.getLabourDay(getYear())));
        holidays.add(addHoliday(HolidayNames.MARTYR, HolidayTypes.NATIONAL, UGHolidayDate.getMartyrDay(getYear())));
        holidays.add(addHoliday(HolidayNames.INDEPENDENCE, HolidayTypes.NATIONAL,
                UGHolidayDate.getIndependenceDay(getYear())));
        holidays.add(addHoliday(HolidayNames.CHRISTMAS, HolidayTypes.NATIONAL, UGHolidayDate.getChristmas(getYear())));
        holidays.add(addHoliday(HolidayNames.BOXING, HolidayTypes.NATIONAL, UGHolidayDate.getBoxingDay(getYear())));

        return holidays;

    }

    protected List<CHoliday> populateDynamicNationalHolidays() {

        List<CHoliday> holidays = new ArrayList<>();
        // Mother's Day - 2nd Sunday of every May
        holidays.add(addHoliday(HolidayNames.MOTHER_DAY, HolidayTypes.OBSERVANCE, getDynamicHoliday(getYear(),
                Month.MAY, 2, DayOfWeek.SUNDAY)));

        // Father's Day - 3rd Wednesday in June
        holidays.add(addHoliday(HolidayNames.FATHER_DAY, HolidayTypes.OBSERVANCE, getDynamicHoliday(getYear(),
                Month.JUNE, 3, DayOfWeek.WEDNESDAY)));

        return holidays;

    }

    public List<CHoliday> getObservedPublicHolidays() {

        List<CHoliday> holidays = new ArrayList<>();
        populateStaticNationalHolidays().forEach(cHoliday -> {
            CDay observedDay = CDay.createDay(roundDate(cHoliday.getDay()
                    .getDate()), getLocale());
            cHoliday.setDay(observedDay);
            holidays.add(cHoliday);

        });
        populateDynamicNationalHolidays().forEach(cHoliday -> {
            CDay observedDay = CDay.createDay(roundDate(cHoliday.getDay()
                    .getDate()), getLocale());
            cHoliday.setDay(observedDay);
            holidays.add(cHoliday);

        });

        Collections.sort(holidays);

        return holidays;
    }

}
