package com.sampleapps.calculators.dto;

import com.sampleapps.calculators.statics.TimeInterval;

public class LoanCalculator {

    private double amount;

    private double term; // in months

    private double rate;

    private double interest;

    private double principal;

    private TimeOption timeOption;

    public double getAmount() {

        return amount;
    }

    public void setAmount(double amount) {

        this.amount = amount;
    }

    public double getInterest() {

        return interest;
    }

    public void setInterest(double interest) {

        this.interest = interest;
    }

    public double getPrincipal() {

        return principal;
    }

    public void setPrincipal(double principal) {

        this.principal = principal;
    }

    public double getRate() {

        return rate;
    }

    public void setRate(double rate) {

        this.rate = rate;
    }

    public double getTerm() {

        return term;
    }

    public void setTerm(double term) {

        this.term = term;
    }

    public TimeOption getTimeOption() {

        return timeOption;
    }

    public void setTimeOption(TimeOption timeOption) {

        this.timeOption = timeOption;
    }

    public void calculateLoanAmount() {

        this.term = calculateLoanTerm();
        this.interest = this.principal * (this.rate * 0.01) * this.term;
        this.amount = this.principal + this.interest;
    }

    private double calculateLoanTerm() {

        TimeInterval interval = this.timeOption.getPeriod();
        double loanTerm = 0;
        switch (interval) {
            case YEARLY -> loanTerm = this.timeOption.getTime() * 12;
            case MONTHLY -> loanTerm = this.timeOption.getTime();
            case WEEKLY -> loanTerm = this.timeOption.getTime() / 4.345;
            case DAILY -> loanTerm = this.timeOption.getTime() / (4.345 * 7);
        }

        return loanTerm;
    }

    public void calculateRate() {

        this.term = calculateLoanTerm();
        if (this.term != 0) {
            this.rate = ((this.amount - this.principal) / (this.principal * this.term)) * 100;
        }
    }

    public void calculatePrincipal() {

        this.term = calculateLoanTerm();
        this.principal = this.amount / ((this.rate * 0.01 * this.term) + 1);
    }

}
