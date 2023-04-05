package com.sampleapps.calculators;

import com.sampleapps.calculators.pojos.LoanCalculator;
import com.sampleapps.calculators.statics.TimeInterval;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Logger;

public class LoanSchedule {

    private static final Logger LOGGER = Logger.getLogger(LoanSchedule.class.getName());

    private LoanSchedule() {}

    public static void getLoanSchedule(Scanner menuInput, LoanCalculator loan) {

        System.out.print("\nDisplay payment schedule. (Y/y)es or (N/n)o :");
        String printOption = menuInput.next();

        if (printOption.equalsIgnoreCase("Y")) {
            System.out.print("\nEnter date. [dd/MM/yyyy]/[yyyy-MM-dd]: ");
            String dateInput = menuInput.next();

            LocalDate disbursementDate = stringToLocalDate(dateInput);
            assert disbursementDate != null;
            LocalDate endDate = disbursementDate.plusMonths((long) loan.getTimeOption()
                    .getTime());

            double principal = loan.getPrincipal() / loan.getTimeOption()
                    .getTime();
            double interest = loan.getInterest() / loan.getTimeOption()
                    .getTime();
            double lineAmount = principal + interest;
            double balance = loan.getAmount();

            TimeInterval interval = loan.getTimeOption()
                    .getPeriod();
            switch (interval) {
                case YEARLY -> endDate = disbursementDate.plusYears((long) loan.getTimeOption()
                        .getTime());
                case MONTHLY -> endDate = disbursementDate.plusMonths((long) loan.getTimeOption()
                        .getTime());
                case WEEKLY -> endDate = disbursementDate.plusWeeks((long) loan.getTimeOption()
                        .getTime());
                case DAILY -> endDate = disbursementDate.plusDays((long) loan.getTimeOption()
                        .getTime());
            }

            System.out.printf("\n" + "%-15s %-15s %-15s %-15s %-15s%n", "Payment Date", "Amount", "Principal", "Interest", "Balance");

            while (disbursementDate.isBefore(endDate)) {

                balance = balance - lineAmount;

                System.out.printf("\n" +
                                  "%-15s %-15.2f %-15.2f %-15.2f %-15.2f", disbursementDate, lineAmount, principal,
                        interest, balance);

                switch (interval) {
                    case YEARLY -> disbursementDate = disbursementDate.plusYears(1L);
                    case MONTHLY -> disbursementDate = disbursementDate.plusMonths(1L);
                    case WEEKLY -> disbursementDate = disbursementDate.plusWeeks(1L);
                    case DAILY -> disbursementDate = disbursementDate.plusDays(1L);
                }

            }

        } else if (!printOption.equalsIgnoreCase("N")) {
            invalidOption();
        }

    }

    private static LocalDate stringToLocalDate(String datetime) {

        final String DATE_FORMATS = "[yyyy-MM-dd][dd/MM/yyyy]";

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATS);

            return LocalDate.parse(datetime, formatter);

        } catch (Exception e) {
            LOGGER.severe(String.format("Error formatting date! %s", e.getLocalizedMessage()));
        }

        return null;

    }


    private static void invalidOption() {

        System.out.println("Invalid option!");

    }

}
