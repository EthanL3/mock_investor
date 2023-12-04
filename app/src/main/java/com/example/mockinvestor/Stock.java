package com.example.mockinvestor;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Stock implements Serializable {
    private String symbol;
    private double volume;
    private double gainLossDollars, gainLossPercent; //total gain/loss of all shares
    private double currentPrice, purchasePrice; //price per share
    private double currentValue, purchaseValue;//value of all shares
    private int shares;
    private Date purchaseDate, currentDate;
    //private String date = "2023-12-01"; //placeholder

    /*public Stock(String symbol, int shares) { //temporary code
        this.symbol = symbol;
        this.shares = shares;
        this.currentPrice = 100;
        this.purchasePrice = 75;
        this.volume = 100000;
        this.T_gainLossDollars = 25;
        this.T_gainLossPercent = 33.33;
    }*/

    public Stock(String symbol, double price, double volume, String date){
        this.symbol = symbol;
        try {
            this.currentPrice = price;
            this.purchasePrice = price;
            this.currentValue = currentPrice * shares;
            this.purchaseValue = purchasePrice * shares;
            this.volume = volume;
        } catch (NumberFormatException e){
            System.out.println("Error: initialization: string not in number format");
        }
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            purchaseDate = date_format.parse(date);
            currentDate = date_format.parse(date);
        } catch (ParseException e) {
            System.out.println("Error: purchaseStock: string not in 'yyyy-MM-dd' format" + e.getMessage());
        }
        updateGainsLoss();
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
    public String getPurchaseDate() {
        return purchaseDate.toString();
    }
    public String getCurrentDate() {
        return currentDate.toString();
    }

    //update day function: to be used every day through clock-imitating loop
    public void updateDay(double price){
        this.currentPrice = price;
        this.currentValue = currentPrice * shares;
        updateGainsLoss();
        //update date by one day in "currentDate"
        Calendar cal_currentDate = Calendar.getInstance();
        cal_currentDate.setTime(currentDate);
        cal_currentDate.add(Calendar.DATE, 1);
        currentDate = cal_currentDate.getTime();
    }

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

    //private functions to be used within class (IGNORE)
    private void updateGainsLoss(){
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
}