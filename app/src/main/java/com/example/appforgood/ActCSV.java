package com.example.appforgood;

import java.util.Locale;

public class ActCSV {
    //Data
    private String name;
    private double cost;
    private boolean atHome;
    private String category;
    
    //Methods
    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public boolean getAtHome() {
        return atHome;
    }

    public String getCategory() {
        return category.toLowerCase(Locale.ROOT);
    }


}
