package com.example.mockinvestor;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Stock implements Serializable {
    //every field marked by T_ is value considering TOTAL SHARES
    private String symbol, companyName;
    private double marketCap, volume;
    private double T_gainLossDollars, T_gainLossPercent;
    private double currentVal, purchaseVal;
    private double T_currentVal, T_purchaseVal, T_valHistory[];
    private int shares, count_day = 0;
    private Date purchaseDate, currentDate;
    private String date = "08/01/2023"; //placeholder

    public Stock(String symbol, int shares) { //temporary code
        this.symbol = symbol;
        this.shares = shares;
    }

    public Stock(String symbol, String companyName, String price, String volume, String marketCap){
        this.symbol = symbol;
        this.companyName = companyName; //will add when I add CSV file
        try {
            this.currentVal = Double.parseDouble(price);
            this.purchaseVal = Double.parseDouble(price);
            this.volume = Double.parseDouble(volume); //will add when I add CSV file
            this.marketCap = Double.parseDouble(marketCap); //will add when I add CSV file
        } catch (NumberFormatException e){
            System.out.println("Error: initialization: string not in number format");
        }
        updateGainsLoss();
    }
    //getters for each variable: to return strings only
    public String getSymbol(){
        return symbol;
    }
    public String getCompanyName(){
        return companyName;
    }
    public int getQuantity(){ //number of shares
        return shares;
    }
    public double getMarketCap() {
        return marketCap;
    }
    public double getVolume() {
        return volume;
    }
    public double getCurrentValue(){ //per share
        return currentVal;
    } //per share
    public double getPurchaseValue() { //per share
        return purchaseVal;
    } //per share
    public double getTOTALCurrentValue(){ //returns total purchase value (x num shares)
        return T_currentVal;
    }
    public double getTOTALPurchaseValue() { //returns total purchase value (x num shares)
        return T_purchaseVal;
    }
    public double getGainLossDollars(){
        return T_gainLossDollars;
    }
    public double getGainLossPercent(){
        return T_gainLossPercent;
    }

    //update day function: to be used every day through clock-imitating loop
    public void updateDay(String price){
        try {
            this.currentVal = Double.parseDouble(price);
        } catch (NumberFormatException e){
            System.out.println("Error: update string not in number format");
        }
        this.T_currentVal = (double) currentVal * shares;
        count_day++;
        this.T_valHistory[count_day] = T_currentVal;
        updateGainsLoss();
        //update date by one day in "currentDate"
        SimpleDateFormat date_format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            purchaseDate = date_format.parse(date);
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
        //update everything after buying shares
        //this.T_currentVal = (numShares + this.shares) * currentVal;
        //this.T_purchaseVal = (numShares + this.shares) * currentVal;
        //this.T_valHistory[count_day] = T_currentVal;
        //updateGainsLoss();

    }

    public void sellShares(int numShares) {
        this.shares -= numShares;
        //updating everything after selling shares
        //this.T_currentVal = (this.shares - numShares) * currentVal;
        //this.T_purchaseVal = (this.shares - numShares) * currentVal;
        //this.T_valHistory[count_day] = T_currentVal;
        //updateGainsLoss();
    }

    //private functions to be used within class (IGNORE)
    private void updateGainsLoss(){
        T_gainLossDollars = T_currentVal - T_purchaseVal;
        T_gainLossPercent = (T_gainLossDollars / T_purchaseVal) * 100; //in percent
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