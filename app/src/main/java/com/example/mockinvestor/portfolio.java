public class Portfolio {

    private String investorName;
    private float cash = 100000;

    private int portfolioSize = 0;
    private int[] shares = new int[100]; // Assuming a maximum of 100 shares in the portfolio
    private StockObj[] portfolio = new StockObj[100]; // Assuming a maximum of 100 stocks in the portfolio

    private float holdingPeriodReturn; // Holding period return: total return of a portfolio over user tenure (saved as percentage)
    private float averageReturn; // Average return of your portfolio per annum

    public Portfolio(String name) {
        investorName = name;
    }

    public void setInvestorName(String name) {
        investorName = name;
    }

    public void buy(StockObj stock, int numShares) {
        shares[portfolioSize] = numShares;
        portfolio[portfolioSize] = stock.multiplyByShares(numShares);
        portfolioSize++;
        cash -= stock.returnMarketValue();
    }

    public void sell(StockObj stock, int numShares) {
        cash += stock.returnMarketValue();
        int count = 0;
        while (!stock.getSymbol().equals(portfolio[count].getSymbol())) {
            if (count == portfolioSize) {
                break;
            }
            count++;
        }
        while (count < portfolioSize - 1) {
            portfolio[count] = portfolio[count + 1];
            count++;
        }
        portfolio[portfolioSize - 1] = null;
        portfolioSize--;
    }
}
