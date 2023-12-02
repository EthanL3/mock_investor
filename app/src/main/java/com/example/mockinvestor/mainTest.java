package com.example.mockinvestor;

public class mainTest {

    public static void main(String[] args) {
        String stockSymbol = "AAPL";

        apicall apiCall = new apicall();

        stock_obj stock = apiCall.fetchStockInfo(stockSymbol);

        // Print stock details
        if (stock != null) {
            stock.printStockDetails();
        } else {
            System.out.println("Failed to fetch stock information.");
        }
    }
}
