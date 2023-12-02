package com.example.mockinvestor;

import java.io.Serializable;

public class Stock {
    private String symbol, companyName;
    private int quantity;
    private double currentValue, gainLoss;

    public Stock(String symbol, int quantity){
        this.symbol = symbol;
        this.companyName = "Company name"; //placeholder
        this.quantity = quantity;
        this.currentValue = 100; //placeholder
        this.gainLoss = 0;
    }
    //create getters and setters for each variable
    public String getSymbol(){
        return symbol;
    }
    public String getCompanyName(){
        return companyName;
    }
    public String getQuantity() {
        return Integer.toString(quantity);
    }
    public String getCurrentValue(){
        return Double.toString(currentValue);
    }
    public String getGainLoss(){
        return Double.toString(gainLoss);
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setCurrentValue(double currentValue){
        this.currentValue = currentValue;
    }
    public void setGainLoss(double gainLoss){
        this.gainLoss = gainLoss;
    }
}
