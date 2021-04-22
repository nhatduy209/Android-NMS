package com.example.notemanagement.ui;

public class RegionalSalesData {
    private String region;
    private int sales;

    public RegionalSalesData(String region, int sales) {
        this.region = region;
        this.sales = sales;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
}
