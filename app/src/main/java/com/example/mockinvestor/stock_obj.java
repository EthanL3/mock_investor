package com.example.mockinvestor;

public class stock_obj{

    private char[] g_symbol;
    private float g_buy_price = 0;
    private float c_market_value = 0;
    private float c_book_value = 0;
    private float c_eps = 0;
    private int count_month = 0;
    private int count_year = 0;
    private float price_earnings_growth = 0;
    private float dividend_yield = 0;
    private float price_book_ratio = 0;
    private float price_earnings_ratio = 0;

    private float[][] eps_history = new float[3][12];
    private float[][] market_val_history = new float[3][12];
    private float[][] book_val_history = new float[3][12];
    private float[] annual_div_history = new float[3];

//FUNCTIONS TO USE OUTSIDE THIS CLASS-----------------------------------------------------------------------------

//GET FUNCTIONS FOR ETHAN
     public char[] getSymbol(){
	return g_symbol;
    }

     public char[] getBuyPrice(){
	return String.valueOf(g_buy_price);
    }
    
    public char[] getMrktValue(){
	return String.valueOf(c_market_value);
    }
	
    public char[] getLossesOrGains(){
	return String.valueOf(c_market_value-g_buy_price);
    }

    public char[] getDetailedInfo(){
	char[] outstring;
	outstring = "Buy Price: " + g_buy_price + "\nMarket Price: " + c_market_value + "\nBook Price: " + c_book_value;
	outstring = outstring + "\nEarnings per Share: " + c_eps + "\nPrice to Growth of Earnings per Share: " + price_earnings_growth;
	outstring = outstring + "\nPrice to Book Ratio: " + price_book_ratio + "\nPrice to Earnings Ratio: " + price_earnings_ratio;
	//outstring = outstring + "\nDividend Yield: " + dividend_yield;
	return outstring;
    }

//FOLLOWING TWO FUNCTIONS ARE FOR MARKET VALUE GRAPHS FOR ETHAN
     public float[] getMrktValueHistory{
	return market_val_history; //every value is 1 month apart, starting at year 0 and month 0
     }

	
//INITIALIZE FUNCTIONS FOR MOUMIT
     public void initialize(char[] symbol, float price){
         initialize(symbol, price, 0, 0);
    }
    
     public void initialize(char[] symbol, float price, float bk_val){
	 initialize(symbol, price, bk_val, 0);
    }
    
    public void initialize(char[] symbol, float price, float bk_val, float eps_val){
	    if ((count_year == 0) && (count_month == 0))
		g_symbol = symbol;
         	g_buy_price = price;
         	c_market_value = price; //market value is buy upon buying
         	c_book_value = bk_val;
        	c_eps = eps_val;
    	    }
	   calculateRatios();
    }

//MONTHLY UPDATE FUNCTIONS FOR MOUMIT
    public void monthlyUpdate(float mrkt_val) {
        monthlyUpdate(mrkt_val, 0, 0);
    }

    public void monthlyUpdate(float mrkt_val, float bk_val) {
        monthlyUpdate(mrkt_val, bk_val, 0);
    }

    public void monthlyUpdate(float mrkt_val, float bk_val, float eps_val) {
        updateCurrentValues(mrkt_val, bk_val, eps_val);
        calculateRatios();
        updateTimeByMonth();
	updateHistory();
    }

    public void endOfYearDiv(float div) {
        if (count_month == 11) {
            annual_div_history[count_year] = div;
            calculateRatios();
        } else {
            System.out.println("Error: Dividends can only be saved at the end of the year");
        }
    }

//PORTFOLIO FUNCTIONS FOR ME
   public float returnFLOAT_MrktValue() {
	return c_market_value;
   }


   public void multiplyByShares(int shares) {
	if ((count_year == 0) && (count_month == 0)){
        	updateCurrentValues(mrkt_val*shares, bk_val*shares, eps_val*shares);
	}
        calculateRatios();
    }

//END OF FUNCTIONS TO USE OUTSIDE CLASS-----------------------------------------------------------------------------

//ignore this test function:
    /*public void printStockDetails() {
        System.out.println("Stock: " + g_symbol);
        System.out.println("Buy Price: " + g_buy_price);
        System.out.println("Market Price: " + c_market_value);
        System.out.println("Book Price: " + c_book_value);
        System.out.println("Earnings per Share: " + c_eps);
        System.out.println("Price to Growth of Earnings per Share: " + price_earnings_growth);
	System.out.println("Dividend Yield: " + dividend_yield);
        System.out.println("Price to Book Ratio: " + price_book_ratio);
        System.out.println("Price to Earnings Ratio: " + price_earnings_ratio);
    }*/

    // Calculation functions

    private void calcPriceEarningsGrowth() {
        float growthEps = (c_eps / eps_history[count_year][count_month]) - 1;
        price_earnings_growth = (c_market_value / c_eps) / growthEps;
    }

    private void calcPriceBookRatio() {
        price_book_ratio = c_market_value / c_book_value;
    }

    private void calcPriceEarningsRatio() {
        price_earnings_ratio = c_market_value / c_eps;
    }

    private void calcDividendYield() {
        if (count_month == 11) {
            dividend_yield = annual_div_history[count_year] / c_market_value;
        } else {
            dividend_yield = annual_div_history[count_year - 1] / c_market_value;
        }
    }

    // Update functions

    private void updateHistory() {
        market_val_history[count_year][count_month] = c_market_value;
        book_val_history[count_year][count_month] = c_book_value;
        eps_history[count_year][count_month] = c_eps;
    }

    public void updateCurrentValues(float mrkt_val, float bk_val, float eps_val) {
        c_market_value = mrkt_val;
        c_book_value = bk_val;
        c_eps = eps_val;
    }

    private void calculateRatios() {
        calcPriceEarningsGrowth();
        calcPriceBookRatio();
        calcPriceEarningsRatio();
        calcDividendYield();
    }

    private void updateTimeByMonth() {
        if ((count_month == 11) && (count_year == 2)) {
            for (int i = 0; i < 11; i++) {
                market_val_history[0][i] = market_val_history[1][i];
                market_val_history[1][i] = market_val_history[2][i];
                book_val_history[0][i] = book_val_history[1][i];
                book_val_history[1][i] = book_val_history[2][i];
                eps_history[0][i] = eps_history[1][i];
                eps_history[1][i] = eps_history[2][i];
            }
            annual_div_history[count_year] = 0;
            count_month = 0;
        } else if ((count_year != 2) && (count_month == 11)) {
            annual_div_history[count_year] = 0;
            count_month = 0;
            count_year++;
        } else {
            count_month++;
        }
    }
}
