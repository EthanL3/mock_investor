public class stock_obj {

    private char[] g_stockID;
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

    public void printStockDetails() {
        System.out.println("Stock: " + g_stockID);
        System.out.println("Buy Price: " + g_buy_price);
        System.out.println("Market Price: " + c_market_value);
        System.out.println("Book Price: " + c_book_value);
        System.out.println("Earnings per Share: " + c_eps);
        System.out.println("Price to Growth of Earnings per Share: " + price_earnings_growth);
	System.out.println("Dividend Yield: " + dividend_yield);
        System.out.println("Price to Book Ratio: " + price_book_ratio);
        System.out.println("Price to Earnings Ratio: " + price_earnings_ratio);
    }

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

    private void updateCurrentValues(float mrkt_val, float bk_val, float eps_val) {
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
