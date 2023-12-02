package com.example.mockinvestor;

import java.io.Serializable;
import java.util.Objects;

public class Stock implements Serializable {
    private String symbol, companyName;
    private int quantity;
    private double gainLossDollars, gainLossPercent, marketCap, volume;
    private String purchaseDate, purchaseTime, currentDate, currentTime;
    private double purchasePrice, purchaseValue, currentPrice, currentValue;
    //Price is price per share, value is price * quantity, gainLoss is currentValue - purchaseValue

    public Stock(String symbol, int quantity){
        this.symbol = symbol;
        this.companyName = "Company name"; //placeholder

        this.purchaseDate = "01/01/2021"; //placeholder
        this.purchaseTime = "12:00"; //placeholder
        this.currentDate = "05/01/2021"; //placeholder
        this.currentTime = "12:00"; //placeholder

        this.quantity = quantity;
        this.currentPrice = 150; //placeholder
        this.currentValue = quantity * currentPrice; //placeholder

        //maybe will add purchaseQuantity and currentQuantity later, but for now assume they are the same
        this.purchasePrice = 100; //placeholder
        this.purchaseValue = quantity * purchasePrice; //placeholder

        this.gainLossDollars = currentValue - purchaseValue;
        this.gainLossPercent = (currentValue - purchaseValue) / purchaseValue;

        this.marketCap = 1000; //placeholder
        this.volume = 1000; //placeholder
    }


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

    public void sellShares(int sharesSold) {
        if (this.quantity== sharesSold) {
            MyApplication.getInstance().removeStockFromList(this);
        }
        else {
            this.quantity -= sharesSold;
            this.currentValue = quantity * currentPrice;
            this.gainLossDollars = currentValue - purchaseValue;
            this.gainLossPercent = (currentValue - purchaseValue) / purchaseValue;
        }
    }

    public void buyShares(int sharesBought) {
        this.quantity += sharesBought;
        this.currentValue = quantity * currentPrice;
        this.gainLossDollars = currentValue - purchaseValue;
        this.gainLossPercent = (currentValue - purchaseValue) / purchaseValue;
    }

    private void updateGainLossDollars(){
        this.gainLossDollars = currentValue - purchaseValue;
    }

    public void updateGainLossPercent(){
        this.gainLossPercent = (currentValue - purchaseValue) / purchaseValue;
    }


    //SETTERS AND GETTERS:
    public double getGainLossPercent() {
        return gainLossPercent;
    }

    public void setGainLossPercent(double gainLossPercent) {
        this.gainLossPercent = gainLossPercent;
    }

    public double getGainLossDollars(){
        return gainLossDollars;
    }

    public void setGainLossDollars(double gainLossDollars){
        this.gainLossDollars = gainLossDollars;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(double purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }
}
