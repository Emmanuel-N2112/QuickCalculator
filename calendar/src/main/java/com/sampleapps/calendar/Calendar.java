package com.sampleapps.calendar;

import com.sampleapps.calendar.views.MenuView;

import java.util.Scanner;
import java.util.logging.Logger;

public class Calendar {
    private static final Logger LOGGER = Logger.getLogger(Calendar.class.getName());

    public static void main(String[] args) {

        System.out.println("********** SIMPLE CALENDAR **********");

        try (Scanner menuInput = new Scanner(System.in)) {

            MenuView.displayHomeMenu(menuInput);

        } catch (Exception e) {
            LOGGER.severe(e.getLocalizedMessage());
        }
    }


}
