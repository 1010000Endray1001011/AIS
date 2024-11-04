package com.kitisgang.aisgasstation.dbClass;

public class Fuel {

    private int id;
    private String name;
    private double cost_per_liter;
    private double octain_number;

    public Fuel(int id, String name, double cost_per_liter, double octain_number) {
        this.id = id;
        this.name = name;
        this.cost_per_liter = cost_per_liter;
        this.octain_number = octain_number;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String  getName() {return name;}
    public void setName(String name) {this.name = name;}

    public double getCost_per_liter() {return cost_per_liter;}
    public void setCost_per_liter(double cost_per_liter) {this.cost_per_liter = cost_per_liter;}

    public double getOctain_number() {return octain_number;}
    public void setOctain_number(double octain_number) {this.octain_number = octain_number;}
}
