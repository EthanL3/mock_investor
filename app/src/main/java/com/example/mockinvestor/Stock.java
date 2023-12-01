package com.example.mockinvestor;

public class Stock {
    private String symbol, name, quantity, currentValue, gainLoss;

    public Stock(String symbol, String name, String quantity, String currentValue, String gainLoss){
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.currentValue = currentValue;
        this.gainLoss = gainLoss;
    }
    //create getters and setters for each variable
    public String getSymbol(){
        return symbol;
    }
    public String getName(){
        return name;
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
    public void setName(String name){
        this.name = name;
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
