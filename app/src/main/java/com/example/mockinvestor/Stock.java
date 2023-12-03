package com.example.mockinvestor;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Stock implements Serializable {
    private String symbol, companyName;
    private double marketCap = 0, volume = 0;
    private double currentPrice = 0, purchasePrice = 0; //price per share
    private double currentValue = 0, purchaseValue = 0;//value of all shares
    private int shares = 0, count_day = 0;
    private Date purchaseDate, currentDate;
    private String date = "08/01/2023"; //placeholder

    public Stock(String symbol, int shares) { //temporary code
        this.symbol = symbol;
        this.shares = shares;
        this.companyName = "Company Name";
        this.currentPrice = 100;
        this.purchasePrice = 75;
        this.volume = 100000;
        this.marketCap = 1000000;
    }

    public Stock(String symbol, String companyName, String price, String volume, String marketCap, String date){
        this.symbol = symbol;
        this.companyName = companyName; //will add when I add CSV file
        try {
            this.currentPrice = Double.parseDouble(price);
            this.purchasePrice = Double.parseDouble(price);
            this.volume = Double.parseDouble(volume); //will add when I add CSV file
            this.marketCap = Double.parseDouble(marketCap); //will add when I add CSV file
        } catch (NumberFormatException e){
            System.out.println("Error: initialization: string not in number format");
        }
        SimpleDateFormat date_format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            purchaseDate = date_format.parse(date);
            currentDate = date_format.parse(date);
        } catch (ParseException e) {
            System.out.println("Error: initialize: string not in MM/dd/yyyy format" + e.getMessage());
        }
    }
    //getters for each variable: to return strings only
    public String getSymbol(){
        return symbol;
    }
    public String getCompanyName(){
        return companyName;
    }
    public int getShares(){ //number of shares
        return shares;
    }
    public double getMarketCap() {
        return marketCap;
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

    //update day function: to be used every day through clock-imitating loop
    public void updateDay(String price){
        try {
            this.currentPrice = Double.parseDouble(price);
        } catch (NumberFormatException e){
            System.out.println("Error: update string not in number format");
        }
        this.currentValue = currentPrice * shares;
        //update date by one day in "currentDate"
        SimpleDateFormat date_format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            currentDate = date_format.parse(date);
        } catch (ParseException e) {
            System.out.println("Error: purchaseStock: string not in MM/dd/yyyy format" + e.getMessage());
        }
        Calendar cal_currentDate = Calendar.getInstance();
        cal_currentDate.setTime(currentDate);
        cal_currentDate.add(Calendar.DATE, 1);
        currentDate = cal_currentDate.getTime();
        count_day++;
    }

    public void buyShares(int numShares) {
        this.shares += numShares;
        this.currentValue = currentPrice * shares;
    }

    public void sellShares(int numShares) {
        this.shares -= numShares;
        this.currentValue = currentPrice * shares;

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
