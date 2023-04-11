package com.sampleapps.calendar.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Logger;

public class TimeConverter {

    private static final Logger LOGGER = Logger.getLogger(TimeConverter.class.getName());

    private static final String DATE_FORMATS = "[yyyy-MM-dd][dd/MM/yyyy]";

    private TimeConverter() {}

    public static String localDateToString(LocalDate date, Locale locale) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM uuuu", locale);

            return date.atStartOfDay()
                    .format(formatter);

        } catch (Exception e) {
            LOGGER.severe(String.format("Error formatting date to string! %s", e.getLocalizedMessage()));
        }

        return null;
    }

    public static LocalDate stringToLocalDate(String datetime, Locale locale) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATS, locale);

            return LocalDate.parse(datetime, formatter);

        } catch (Exception e) {
            LOGGER.severe(String.format("Error formatting string to date! %s", e.getLocalizedMessage()));
        }

        return null;

    }

}
