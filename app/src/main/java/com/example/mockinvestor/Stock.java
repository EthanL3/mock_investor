package com.example.mockinvestor;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Stock implements Serializable {
    private String symbol;
    private int daysSincePurchase, purchaseDay;
    private double volume;
    private double gainLossDollars, gainLossPercent; //total gain/loss of all shares
    private double currentPrice, purchasePrice; //price per share
    private double currentValue, purchaseValue;//value of all shares
    private int shares;

    public Stock(String symbol, double price, double volume) {
        this.symbol = symbol;
        try {
            this.currentPrice = price;
            this.purchasePrice = price;
            this.currentValue = currentPrice * shares;
            this.purchaseValue = purchasePrice * shares;
            this.volume = volume;
            this.daysSincePurchase = 0;
            this.purchaseDay = MyApplication.getInstance().getDayCount();
        } catch (NumberFormatException e){
            System.out.println("Error: initialization: string not in number format");
        }
        updateGainsLoss();
    }

    public int getPurchaseDay() {
        return purchaseDay;
    }
    public String getSymbol(){
        return symbol;
    }
    public int getShares(){ //number of shares
        return shares;
    }
    public double getVolume() {
        return volume;
    }
    public double getCurrentPrice(){ //per share
        return currentPrice;
    } //per share
    public double getPurchasePrice() { //per share
        return purchasePrice;
    } //per share
    public double getCurrentValue() {
        this.currentValue = currentPrice * shares; //TEMPORARY
        return currentValue;
    }
    public double getPurchaseValue() {
        this.purchaseValue = purchasePrice * shares; //TEMPORARY
        return purchaseValue;
    }
    public double getGainLossDollars(){
        return gainLossDollars;
    }
    public double getGainLossPercent(){
        return gainLossPercent;
    }
    /*
    public String getPurchaseDate() {
        return purchaseDate.toString();
    }
    public String getCurrentDate() {
        return currentDate.toString();
    }
    */
    public int getDaysSincePurchase() {
        return daysSincePurchase;
    }

    //update day function: to be used every day through clock-imitating loop

    public void buyShares(int numShares) {
        this.shares += numShares;
        this.currentValue = shares * currentPrice;
        this.purchaseValue += numShares * currentPrice;
    }

    public void sellShares(int numShares) {
        this.shares -= numShares;
        this.currentValue = shares * currentPrice;
        this.purchaseValue -= numShares * currentPrice;
    }

    public void updateGainsLoss(){
        gainLossDollars = currentValue - purchaseValue;
        gainLossPercent = (gainLossDollars / purchaseValue) * 100; //in percent
    }

    //assumes symbol is unique for each stock
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(symbol, stock.symbol);
    }
    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }

    public void setDaysSincePurchase(int daysSincePurchase) {
        this.daysSincePurchase = daysSincePurchase;
    }

    public void setShares(int shares){
        this.shares = shares;
    }

    public void setCurrentValue(double v) {
        this.currentValue = v;
    }

    public void setPurchaseValue(double v) {
        this.purchaseValue = v;
    }

    public void setCurrentPrice(float closePrice) {
        this.currentPrice = closePrice;
    }

    public void setGainLossDollars(double v) {
        this.gainLossDollars = v;
    }
    public void setGainLossPercent(double v) {
        this.gainLossPercent = v;
    }
}