package com.example.appforgood;

import java.util.Locale;

public class Activity {
    //Data
    private String name;
    private double cost;
    private boolean atHome;
    private String category;
    private String pic;

    //Constructor
    public Activity (String inputName, double inputCost, boolean inputAtHome, String inputCategory, String inputPic){
        name = inputName;
        cost = inputCost;
        atHome = inputAtHome;
        category = inputCategory;
        pic = inputPic;
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

    public String getPic() {return pic;}


}
