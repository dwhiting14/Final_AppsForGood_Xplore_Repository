package com.example.appforgood;

import java.util.Locale;

public class Activity {
    //Data
    private String name;
    private double cost;
    private boolean atHome;
    private String category;

    //Constructor
    public Activity (String inputName, double inputCost, boolean inputAtHome, String inputCategory){
        name = inputName;
        cost = inputCost;
        atHome = inputAtHome;
        category = inputCategory;
    }
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
