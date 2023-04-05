package com.sampleapps.calculators.pojos;

public class VATCalculator {

    private double netAmount;

    private double vat;

    private double grossAmount;

    private double rate;

    public double getNetAmount() {

        return netAmount;
    }

    public void setNetAmount(double netAmount) {

        this.netAmount = netAmount;
    }

    public double getVat() {

        return vat;
    }

    public double getGrossAmount() {

        return grossAmount;
    }

    public void setGrossAmount(double grossAmount) {

        this.grossAmount = grossAmount;
    }

    public void calculateNetAmount() {

        this.rate = 0.18;
        this.netAmount = this.grossAmount / (1 + this.rate);
        this.vat = this.grossAmount - this.netAmount;
    }

    public void calculateGrossAmount() {

        this.rate = 0.18;
        this.vat = this.netAmount + (this.netAmount * this.rate);
        this.grossAmount = this.grossAmount + this.vat;
    }

    public void calculateRate() {

        this.rate = ((this.grossAmount / this.netAmount) - 1);
        this.vat = this.grossAmount - this.netAmount;
    }

    public double getRate() {

        return rate;
    }

}
