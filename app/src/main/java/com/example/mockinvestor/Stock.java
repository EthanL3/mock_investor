package com.example.mockinvestor;

import com.github.mikephil.charting.data.Entry;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Stock implements Serializable {
    private String symbol, name;
    private int daysSincePurchase, purchaseDay;
    private double volume;
    private double gainLossDollars, gainLossPercent; //total gain/loss of all shares
    private double currentPrice, purchasePrice; //price per share
    private double currentValue, purchaseValue;//value of all shares
    private int shares;
    private ArrayList<Entry> dataVals;

    public Stock(String symbol, double price, double volume) {
        this.symbol = symbol;
        try {
            this.currentPrice = price;
            this.purchasePrice = price;
            this.currentValue = currentPrice * shares;
            this.purchaseValue = purchasePrice * shares;
            this.volume = volume;
            this.daysSincePurchase = 0;
            this.purchaseDay = MyApplication.getInstance().getDayCount();
            this.name = setName();
            this.dataVals = new ArrayList<>();
        } catch (NumberFormatException e){
            System.out.println("Error: initialization: string not in number format");
        }
        updateGainsLoss();
    }

    public int getPurchaseDay() {
        return purchaseDay;
    }
    public String getSymbol(){
        return symbol;
    }
    public int getShares(){ //number of shares
        return shares;
    }
    public double getVolume() {
        return volume;
    }
    public double getCurrentPrice(){ //per share
        return currentPrice;
    } //per share
    public double getPurchasePrice() { //per share
        return purchasePrice;
    } //per share
    public double getCurrentValue() {
        this.currentValue = currentPrice * shares; //TEMPORARY
        return currentValue;
    }
    public double getPurchaseValue() {
        this.purchaseValue = purchasePrice * shares; //TEMPORARY
        return purchaseValue;
    }
    public double getGainLossDollars(){
        return gainLossDollars;
    }
    public double getGainLossPercent(){
        return gainLossPercent;
    }
    /*
    public String getPurchaseDate() {
        return purchaseDate.toString();
    }
    public String getCurrentDate() {
        return currentDate.toString();
    }
    */
    public int getDaysSincePurchase() {
        return daysSincePurchase;
    }

    //update day function: to be used every day through clock-imitating loop

    public void buyShares(int numShares) {
        this.shares += numShares;
        this.currentValue = shares * currentPrice;
        this.purchaseValue += numShares * currentPrice;
    }

    public void sellShares(int numShares) {
        this.shares -= numShares;
        this.currentValue = shares * currentPrice;
        this.purchaseValue -= numShares * currentPrice;
    }

    public void updateGainsLoss(){
        gainLossDollars = currentValue - purchaseValue;
        gainLossPercent = (gainLossDollars / purchaseValue) * 100; //in percent
    }

    //assumes symbol is unique for each stock
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


    public void setDataVals() {
        dataVals.clear();
        for(int i = 0; i <= MyApplication.getInstance().getDayCount(); i++)
        {
            dataVals.add(new Entry(i, CSVReader.getClosePrice(i, symbol)));
        }
    }

    public ArrayList<Entry> getDataVals() {
        return this.dataVals;
    }

    public void setDaysSincePurchase(int daysSincePurchase) {
        this.daysSincePurchase = daysSincePurchase;
    }

    public void setShares(int shares){
        this.shares = shares;
    }

    public void setCurrentValue(double v) {
        this.currentValue = v;
    }

    public void setPurchaseValue(double v) {
        this.purchaseValue = v;
    }

    public void setCurrentPrice(float closePrice) {
        this.currentPrice = closePrice;
    }

    public void setGainLossDollars(double v) {
        this.gainLossDollars = v;
    }
    public void setGainLossPercent(double v) {
        this.gainLossPercent = v;
    }

    public String getName() {
        return this.name;
    }

    public String setName()
    {
        switch(this.symbol) {
            case ("AAPL"):
                return "Apple Inc.";
            case ("MSFT"):
                return "Microsoft Corporation";
            case ("AMZN"):
                return "Amazon.com, Inc.";
            case ("GOOG"):
                return "Alphabet Inc.";
            case ("FB"):
                return "Facebook, Inc.";
            case ("TSLA"):
                return "Tesla, Inc.";
            case ("BRK-A"):
                return "Berkshire Hathaway Inc.";
            case ("BRK-B"):
                return "Berkshire Hathaway Inc.";
            case ("INTC"):
                return "Intel Corporation";
            case ("NVDA"):
                return "NVIDIA Corporation";
            case ("AMD"):
                return "Advanced Micro Devices, Inc.";
            case ("JNJ"):
                return "Johnson & Johnson";
            case ("JPM"):
                return "JPMorgan Chase & Co.";
            case ("V"):
                return "Visa Inc.";
            case ("PG"):
                return "The Procter & Gamble Company";
            case ("MA"):
                return "Mastercard Incorporated";
            case ("UNH"):
                return "UnitedHealth Group Incorporated";
            case ("HD"):
                return "The Home Depot, Inc.";
            case ("DIS"):
                return "The Walt Disney Company";
            case ("BAC"):
                return "Bank of America Corporation";
            case ("PYPL"):
                return "PayPal Holdings, Inc.";
            case ("ADBE"):
                return "Adobe Inc.";
            case ("CMCSA"):
                return "Comcast Corporation";
            case ("VZ"):
                return "Verizon Communications Inc.";
            case ("NFLX"):
                return "Netflix, Inc.";
            case ("KO"):
                return "The Coca-Cola Company";
            case ("PFE"):
                return "Pfizer Inc.";
            case ("T"):
                return "AT&T Inc.";
            case ("NKE"):
                return "NIKE, Inc.";
            case ("MRK"):
                return "Merck & Co., Inc.";
            case ("INTU"):
                return "Intuit Inc.";
            case ("CSCO"):
                return "Cisco Systems, Inc.";
            case ("WMT"):
                return "Walmart Inc.";
            case ("CRM"):
                return "salesforce.com, inc.";
            case ("ABT"):
                return "Abbott Laboratories";
            case ("ABBV"):
                return "AbbVie Inc.";
            case ("XOM"):
                return "Exxon Mobil Corporation";
            case ("CVX"):
                return "Chevron Corporation";
            case ("TMO"):
                return "Thermo Fisher Scientific Inc.";
            case ("ACN"):
                return "Accenture plc";
            case ("COST"):
                return "Costco Wholesale Corporation";
            case ("DHR"):
                return "Danaher Corporation";
            case ("MDT"):
                return "Medtronic plc";
            case ("AVGO"):
                return "Broadcom Inc.";
            case ("TXN"):
                return "Texas Instruments Incorporated";
            case ("NEE"):
                return "NextEra Energy, Inc.";
            case ("UNP"):
                return "Union Pacific Corporation";
            case ("LIN"):
                return "Linde plc";
            case ("HON"):
                return "Honeywell International Inc.";
            case ("AMGN"):
                return "Amgen Inc.";
            case ("UPS"):
                return "United Parcel Service, Inc.";
            case ("SBUX"):
                return "Starbucks Corporation";
            case ("PM"):
                return "Philip Morris International Inc.";
            case ("LLY"):
                return "Eli Lilly and Company";
            case ("ORCL"):
                return "Oracle Corporation";
            case ("IBM"):
                return "International Business Machines Corporation";
            case ("MMM"):
                return "3M Company";
            case ("GE"):
                return "General Electric Company";
            case ("MO"):
                return "Altria Group, Inc.";
            case ("AMT"):
                return "American Tower Corporation";
            case ("BLK"):
                return "BlackRock, Inc.";
            case ("NOW"):
                return "ServiceNow, Inc.";
            case ("SPGI"):
                return "S&P Global Inc.";
            case ("AXP"):
                return "American Express Company";
            case ("MDLZ"):
                return "Mondelez International, Inc.";
            case ("CAT"):
                return "Caterpillar Inc.";
            case ("GILD"):
                return "Gilead Sciences, Inc.";
            case ("CHTR"):
                return "Charter Communications, Inc.";
            case ("LMT"):
                return "Lockheed Martin Corporation";
            case ("RTX"):
                return "Raytheon Technologies Corporation";
            case ("TGT"):
                return "Target Corporation";
            case ("FIS"):
                return "Fidelity National Information Services, Inc.";
            case ("MS"):
                return "Morgan Stanley";
            case ("BDX"):
                return "Becton, Dickinson and Company";
            case ("CI"):
                return "Cigna Corporation";
            case ("BKNG"):
                return "Booking Holdings Inc.";
            case ("ISRG"):
                return "Intuitive Surgical, Inc.";
            case ("SYK"):
                return "Stryker Corporation";
            case ("DE"):
                return "Deere & Company";
            case ("ANTM"):
                return "Anthem, Inc.";
            case ("PLD"):
                return "Prologis, Inc.";
            case ("ZTS"):
                return "Zoetis Inc.";
            case ("MU"):
                return "Micron Technology, Inc.";
            case ("WFC"):
                return "Wells Fargo & Company";
            case ("USB"):
                return "U.S. Bancorp";
            case ("GS"):
                return "The Goldman Sachs Group, Inc.";
            case("MRNA"):
                return "Moderna, Inc.";
            //continue pattern for NASDAQ stocks:
            case ("ATVI"):
                return "Activision Blizzard, Inc.";
            case ("ADP"):
                return "Automatic Data Processing, Inc.";
            case ("CSX"):
                return "CSX Corporation";
            case ("D"):
                return "Dominion Energy, Inc.";
            case ("DOW"):
                return "Dow Inc.";
            case ("EQIX"):
                return "Equinix, Inc.";
            case ("GM"):
                return "General Motors Company";
            case ("ICE"):
                return "Intercontinental Exchange, Inc.";
            case ("KMB"):
                return "Kimberly-Clark Corporation";
            case ("LRCX"):
                return "Lam Research Corporation";
            case ("MET"):
                return "MetLife, Inc.";
            case ("MCD"):
                return "McDonald's Corporation";
            case ("MSI"):
                return "Motorola Solutions, Inc.";
            case ("NSC"):
                return "Norfolk Southern Corporation";
            case ("NOC"):
                return "Northrop Grumman Corporation";
            case ("PNC"):
                return "The PNC Financial Services Group, Inc.";
            case ("RTN"):
                return "Raytheon Technologies Corporation";
            case ("SO"):
                return "The Southern Company";
            case ("TMUS"):
                return "T-Mobile US, Inc.";
            case("TJX"):
                return "The TJX Companies, Inc.";
            case ("UBER"):
                return "Uber Technologies, Inc.";
            case ("VFC"):
                return "V.F. Corporation";
            case ("WBA"):
                return "Walgreens Boots Alliance, Inc.";
            case ("WELL"):
                return "Welltower Inc.";
            case ("WMB"):
                return "The Williams Companies, Inc.";
            case ("ZBH"):
                return "Zimmer Biomet Holdings, Inc.";
            case ("ZION"):
                return "Zions Bancorporation, National Association";
            case ("ZM"):
                return "Zoom Video Communications, Inc.";
            case ("A"):
                return "Agilent Technologies, Inc.";
            case ("AAL"):
                return "American Airlines Group Inc.";
            case ("AAP"):
                return "Advance Auto Parts, Inc.";
            case ("ABC"):
                return "AmerisourceBergen Corporation";
            case ("ABMD"):
                return "ABIOMED, Inc.";
            case ("ADM"):
                return "Archer-Daniels-Midland Company";
            case ("AEE"):
                return "Ameren Corporation";
            case ("WM"):
                return "Waste Management, Inc.";
            case("VOO"):
                return "Vanguard S&P 500 ETF";
            case("VTI"):
                return "Vanguard Total Stock Market ETF";
            case("VXUS"):
                return "Vanguard Total International Stock ETF";
            case("BND"):
                return "Vanguard Total Bond Market ETF";
            case("BNDX"):
                return "Vanguard Total International Bond ETF";
            case("VIG"):
                return "Vanguard Dividend Appreciation ETF";
            case("VYM"):
                return "Vanguard High Dividend Yield ETF";
            case("VTV"):
                return "Vanguard Value ETF";
            case("VO"):
                return "Vanguard Mid-Cap ETF";
            case("VB"):
                return "Vanguard Small-Cap ETF";
            case("VBR"):
                return "Vanguard Small-Cap Value ETF";
            case("VEA"):
                return "Vanguard FTSE Developed Markets ETF";
            case("VWO"):
                return "Vanguard FTSE Emerging Markets ETF";
            case("VNQ"):
                return "Vanguard Real Estate ETF";
            case("VPU"):
                return "Vanguard Utilities ETF";
            case("VHT"):
                return "Vanguard Health Care ETF";
            case("VCR"):
                return "Vanguard Consumer Discretionary ETF";
            case("VDC"):
                return "Vanguard Consumer Staples ETF";
            case("VDE"):
                return "Vanguard Energy ETF";
            case("VIS"):
                return "Vanguard Industrials ETF";
            case("VAW"):
                return "Vanguard Materials ETF";
            case("VFH"):
                return "Vanguard Financials ETF";
            case("VGT"):
                return "Vanguard Information Technology ETF";
            case("CVS"):
                return "CVS Health Corporation";
            case("C"):
                return "Citigroup Inc.";
            case("CME"):
                return "CME Group Inc.";
            case("COF"):
                return "Capital One Financial Corporation";
            case("COP"):
                return "ConocoPhillips";
            case("CPRT"):
                return "Copart, Inc.";
            case("YHOO"):
                return "Yahoo! Inc.";
            case("EBAY"):
                return "eBay Inc.";
            case("EA"):
                return "Electronic Arts Inc.";
            case("EXPE"):
                return "Expedia Group, Inc.";
            case("EXPD"):
                return "Expeditors International of Washington, Inc.";
            case("ESRX"):
                return "Express Scripts Holding Company";
            case("XEL"):
                return "Xcel Energy Inc.";
            case("XRX"):
                return "Xerox Corporation";
            case("XLNX"):
                return "Xilinx, Inc.";
            case("XL"):
                return "XL Group Ltd";
            case("XPO"):
                return "XPO Logistics, Inc.";
            case("XYL"):
                return "Xylem Inc.";
            case("YUM"):
                return "Yum! Brands, Inc.";
            case("SCHD"):
                return "Schwab US Dividend Equity ETF";
            case("SCHB"):
                return "Schwab US Broad Market ETF";
            default:
                return "";
        }

    }
}