package com.kitisgang.aisgasstation.dbClass;

public class fuelPump {

    private int fuel_id;
    private int pump_id;
    private String fuel_name;
    private boolean status;
    private double fuel_quantity;

    public fuelPump(int pump_id, int fuel_id,String fuel_name, boolean status, double fuel_quantity) {
        this.fuel_name = fuel_name;
        this.fuel_id = fuel_id;
        this.pump_id = pump_id;
        this.status = status;
        this.fuel_quantity = fuel_quantity;
    }

    public String getFuel_name() {return fuel_name;}
    public int getFuel_id() {return fuel_id;}
    public int getPump_id() {return pump_id;}
    public boolean getStatus() {return status;}
    public double getFuel_quantity() {return fuel_quantity;}

    public void setFuel_name(String fuel_name) {this.fuel_name = fuel_name;}
    public void setFuel_id(int fuel_id) {this.fuel_id = fuel_id;}
    public void setPump_id(int pump_id) {this.pump_id = pump_id;}
    public void setStatus(boolean status) {this.status = status;}
    public void setFuel_quantity(double fuel_quantity) {this.fuel_quantity = fuel_quantity;}

}
