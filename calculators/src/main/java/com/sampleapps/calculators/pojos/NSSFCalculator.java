package com.sampleapps.calculators.pojos;

public class NSSFCalculator {

    private static final double EMPLOYEE_RATE = 0.05;

    private static final double EMPLOYER_RATE = 0.1;

    private double grossPay;

    private double employerContribution;

    private double employeeContribution;

    public double getGrossPay() {

        return grossPay;
    }

    public void setGrossPay(double grossPay) {

        this.grossPay = grossPay;
    }

    public double getEmployerContribution() {

        return employerContribution;
    }

    public void setEmployerContribution(double employerContribution) {

        this.employerContribution = employerContribution;
    }

    public double getEmployeeContribution() {

        return employeeContribution;
    }

    public void setEmployeeContribution(double employeeContribution) {

        this.employeeContribution = employeeContribution;
    }

    public void calculateNSSFDeduction() {

        this.employerContribution = EMPLOYER_RATE * this.grossPay;
        this.employeeContribution = EMPLOYEE_RATE * this.grossPay;
    }

    public void calculateGrossFromEmployerContribution() {

        this.grossPay = this.employerContribution / EMPLOYER_RATE;
        this.employeeContribution = EMPLOYEE_RATE * this.grossPay;
    }

    public void calculateGrossFromEmployeeContribution() {

        this.grossPay = this.employeeContribution / EMPLOYEE_RATE;
        this.employerContribution = EMPLOYER_RATE * this.grossPay;
    }

    public double getNSSF() {

        return this.employerContribution + this.employeeContribution;
    }

    public double getNetPay() {

        return this.grossPay - this.employeeContribution;
    }

}
