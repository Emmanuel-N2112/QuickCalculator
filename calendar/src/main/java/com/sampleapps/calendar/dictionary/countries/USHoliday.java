package com.sampleapps.calendar.dictionary.countries;

import com.sampleapps.calendar.dictionary.HolidayDictionary;
import com.sampleapps.calendar.dictionary.dates.USHolidayDate;
import com.sampleapps.calendar.dto.CDay;
import com.sampleapps.calendar.dto.CHoliday;
import com.sampleapps.calendar.statics.HolidayNames;
import com.sampleapps.calendar.statics.HolidayTypes;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class USHoliday extends HolidayDictionary {

    private static final String NAME = "US";

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

    public List<CHoliday> populateStaticNationalHolidays() {

        List<CHoliday> holidays = new ArrayList<>();
        holidays.add(addHoliday(HolidayNames.NEW_YEAR, HolidayTypes.NATIONAL, USHolidayDate.getNewYears(getYear())));
        holidays.add(addHoliday(HolidayNames.VALENTINE, HolidayTypes.NATIONAL,
                USHolidayDate.getValentineDay(getYear())));
        holidays.add(addHoliday(HolidayNames.GOOD_FRIDAY, HolidayTypes.NATIONAL,
                USHolidayDate.getGoodFriday(getYear())));
        holidays.add(addHoliday(HolidayNames.EASTER, HolidayTypes.NATIONAL, USHolidayDate.getEasterDay(getYear())));
        holidays.add(addHoliday(HolidayNames.EASTER_MONDAY, HolidayTypes.NATIONAL,
                USHolidayDate.getEasterMonday(getYear())));
        holidays.add(addHoliday(HolidayNames.CHRISTMAS, HolidayTypes.NATIONAL, USHolidayDate.getChristmas(getYear())));
        holidays.add(addHoliday(HolidayNames.BOXING, HolidayTypes.NATIONAL, USHolidayDate.getBoxingDay(getYear())));
        holidays.add(addHoliday(HolidayNames.NEW_YEAR_EVE, HolidayTypes.NATIONAL,
                USHolidayDate.getNewYearsEve(getYear())));

        return holidays;

    }

    public List<CHoliday> populateDynamicNationalHolidays() {

        List<CHoliday> holidays = new ArrayList<>();
        // Mother's Day - 2nd Monday of every May
        holidays.add(addHoliday(HolidayNames.MOTHER_DAY, HolidayTypes.OBSERVANCE, getDynamicHoliday(getYear(),
                Month.MAY, 2, DayOfWeek.MONDAY)));

        // Father's Day - 3rd Sunday in June
        holidays.add(addHoliday(HolidayNames.FATHER_DAY, HolidayTypes.OBSERVANCE, getDynamicHoliday(getYear(),
                Month.JUNE, 3, DayOfWeek.SUNDAY)));

        // Labor Day - 1st Monday in September
        holidays.add(addHoliday(HolidayNames.LABOUR, HolidayTypes.NATIONAL, getDynamicHoliday(getYear(),
                Month.SEPTEMBER, 1, DayOfWeek.MONDAY)));

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
