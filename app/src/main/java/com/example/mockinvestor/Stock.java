package com.example.mockinvestor;

import java.io.Serializable;

public class Stock implements Serializable {
    private String symbol, companyName, quantity, currentValue, gainLoss;

    public Stock(String symbol, String companyName, String quantity, String currentValue, String gainLoss){
        this.symbol = symbol;
        this.companyName = companyName;
        this.quantity = quantity;
        this.currentValue = currentValue;
        this.gainLoss = gainLoss;
    }
    //create getters and setters for each variable
    public String getSymbol(){
        return symbol;
    }
    public String getCompanyName(){
        return companyName;
    }
    public String getQuantity(){
        return quantity;
    }
    public String getCurrentValue(){
        return currentValue;
    }
    public String getGainLoss(){
        return gainLoss;
    }
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public void setQuantity(String quantity){
        this.quantity = quantity;
    }
    public void setCurrentValue(String currentValue){
        this.currentValue = currentValue;
    }
    public void setGainLoss(String gainLoss){
        this.gainLoss = gainLoss;
    }
}
