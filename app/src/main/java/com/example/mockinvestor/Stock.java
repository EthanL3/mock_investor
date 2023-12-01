package com.example.mockinvestor;

public class Stock {
    private String name;
    private String value;
    private String gains;
    private String shares;

    public Stock(String name, String shares, String value, String gains) {
        this.name = name;
        this.value = value;
        this.gains = gains;
        this.shares = shares;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGains() {
        return gains;
    }

    public void setGains(String gains) {
        this.gains = gains;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }
}
