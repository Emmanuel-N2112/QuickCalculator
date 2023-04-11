package com.sampleapps.calendar.util;

public class PrintOption {

    private PrintOption() {}

    public static void printDottedLine(int number) {

        System.out.println();

        for (int i = 0; i < number; i++) {
            System.out.print("--");
        }
    }
}
