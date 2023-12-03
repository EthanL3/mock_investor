package com.example.mockinvestor;

import java.util.ArrayList;

class Portfolio {
    ArrayList<Stock> stocks;
    double holdings = 0, cash = 100000;
    int numStocks = 0;
    String user_name;

    public Portfolio(){}
    public ArrayList<Stock> getStockList(){ return stocks; }
    public int getNumStocks(){ return numStocks; }
    public void purchaseStocks(Stock stock, int shares) {
        int count = 0;
        while (count <= numStocks) {
            if (stocks.get(count).getSymbol() == stock.getSymbol()) { //add shares if stock already in portfolio
                stocks.get(count).updateShares((stocks.get(count).getQuantity() + shares));
                break;
            }
            count++;
        }
        if (count > numStocks) { //if stock does not already exist in portfolio
            stocks.get(count).updateShares(shares);
        }
        cash = cash - stocks.get(count).getTOTALCurrentValue();
        holdings = holdings + stocks.get(count).getTOTALCurrentValue();
        numStocks++;
    }
    public void setStocksList(ArrayList<Stock> stocks){
        this.stocks = stocks;
    }
    public void sellStocks(Stock stock, int shares) {
        int count = 0;
        int shares_buffer;
        while (count <= numStocks) {
            if (stocks.get(count).getSymbol() == stock.getSymbol()) { //add shares if stock already in portfolio
                shares_buffer = stocks.get(count).getQuantity() - shares;
                if (shares_buffer < 1) {
                    stocks.remove(count);
                } else {
                    stocks.get(count).updateShares(shares_buffer);
                }
                break;
            } //if stock is found in portfolio bracket
            count++;
        }
        if (count > numStocks) { //if stock does not already exist in portfolio
            System.out.println("Error: This stock does not exist in your portfolio");
        }
        cash = cash + stocks.get(count).getTOTALCurrentValue();
        holdings = holdings - stocks.get(count).getTOTALCurrentValue();
        numStocks--;
    }
}
