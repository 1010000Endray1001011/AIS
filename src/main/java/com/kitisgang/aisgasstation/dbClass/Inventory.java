package com.kitisgang.aisgasstation.dbClass;

public class Inventory {
    private int id;
    private String name;
    private int how_much;

    public Inventory(int id, String inventoryName, int howMany) {
        this.id = id;
        this.name = inventoryName;
        this.how_much = howMany;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public int getHow_much() {return how_much;}

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setHow_much(int how_much) {this.how_much = how_much;}
}
