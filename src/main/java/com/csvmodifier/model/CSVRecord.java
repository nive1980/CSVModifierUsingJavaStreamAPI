package com.csvmodifier.model;

public class CSVRecord {
    public CSVRecord(String key, String country, int sumClicks, int sumImps, double sumRev) {
        this.Date = key;
        this.country = country;
        this.clicks=sumClicks;
        this.impressions = sumImps;
        this.revenue = sumRev;
    }

    public CSVRecord() {

    }

    public String getClient() {
        return client;
    }

    public void setClient(String cliient) {
        this.client = cliient;
    }

    private String client;
    private String Date;
    private String mediaSize;
    private String country;
    private int impressions;
    private int clicks;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMediaSize() {
        return mediaSize;
    }

    public void setMediaSize(String mediaSize) {
        this.mediaSize = mediaSize;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getImpressions() {
        return impressions;
    }

    public void setImpressions(int impressions) {
        this.impressions = impressions;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public double getRevenue() {
        return this.revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    private double revenue;


    public void setclicks(int intValue) {
        this.clicks = intValue;
    }
}
