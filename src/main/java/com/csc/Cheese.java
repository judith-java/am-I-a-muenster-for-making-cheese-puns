package com.csc.model;

public class Cheese {
    private final int id;
    private final String milkTreatment;
    private final boolean organic;
    private final Double moisturePercent;
    private final String milkType;
    private final String flavourEn;

    public Cheese(int id, String milkTreatment, boolean organic,
                  Double moisturePercent, String milkType, String flavourEn) {
        this.id = id;
        this.milkTreatment = milkTreatment;
        this.organic = organic;
        this.moisturePercent = moisturePercent;
        this.milkType = milkType;
        this.flavourEn = flavourEn;
    }

    public int getId() { return id; }
    public String getMilkTreatment() { return milkTreatment; }
    public boolean isOrganic() { return organic; }
    public Double getMoisturePercent() { return moisturePercent; }
    public String getMilkType() { return milkType; }
    public String getFlavourEn() { return flavourEn; }
}
