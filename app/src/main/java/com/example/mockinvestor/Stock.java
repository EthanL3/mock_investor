example.mockinvestor;

import java.io.Serializable;
import java.util.Objects;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.Calendar;

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
    }
    public double getPurchaseValue() { //per share
        return purchaseVal;
    }
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
        Calendar cal_currentDate = Calendar.getInstance();
        cal_currentDate.setTime(currentDate);
        cal_currentDate.add(Calendar.DATE, 1);
        currentDate = cal_currentDate.getTime();
    }
    //purchase stock function, to be accessed from portfolio
    public void updateShares(int numShares){
        this.shares = numShares;
        SimpleDateFormat date_format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            purchaseDate = date_format.parse(date);
            currentDate = date_format.parse(date);
        } catch (ParseException e) {
            System.out.println("Error: purchaseStock: string not in MM/dd/yyyy format" + e.getMessage());
        }
        this.T_currentVal = (double) shares * currentVal;
        this.T_purchaseVal = (double) shares * purchaseVal;
        count_day++;
        this.T_valHistory[count_day] = T_currentVal;
        updateGainsLoss();
    }

    //private functions to be used within class (IGNORE)
    private void updateGainsLoss(){
        T_gainLossDollars = T_currentVal - T_purchaseVal;
        T_gainLossPercent = (double) (T_gainLossDollars/T_purchaseVal) - 1; //saves float btw 0-1
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

    /*public void setSymbol(String symbol){
        this.symbol = symbol;
    }
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public void setQuantity(String quantity){
        this.quantity = quantity;
    }
    public void setCurrentValue(String currentValue){
        this.currentVal = currentValue;
    }
    public void setGainLoss(String gainLoss){
        this.gainLoss = gainLoss;
    }*/
}
