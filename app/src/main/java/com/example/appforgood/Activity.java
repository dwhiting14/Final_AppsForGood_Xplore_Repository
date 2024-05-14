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
    //Return: name
    public String getName() {
        return name;
    }

    //Return: cost
    public double getCost() {
        return cost;
    }

    //Return: atHome
    public boolean getAtHome() {
        return atHome;
    }

    //Return: category in lowercase
    public String getCategory() {
        return category.toLowerCase(Locale.ROOT);
    }

    //return: pictire
    public String getPic() {
        return pic;
    }


}
