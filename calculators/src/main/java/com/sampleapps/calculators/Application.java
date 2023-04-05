package com.sampleapps.calculators;

import com.sampleapps.calculators.vews.MenuView;

import java.util.Scanner;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {

        System.out.println("********** QUICK CALCULATOR **********");

        try (Scanner menuInput = new Scanner(System.in)) {

            MenuView.displayHomeMenu(menuInput);

        } catch (Exception e) {
            LOGGER.severe(e.getLocalizedMessage());
        }
    }

}
