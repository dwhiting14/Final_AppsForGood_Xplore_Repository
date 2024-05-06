package com.example.appforgood;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Controller extends Application {

    //bring in all data and logic
    ArrayList<Activity> activities = new ArrayList<>();
    ArrayList preferences = new ArrayList();
    ArrayList dislikes = new ArrayList();
    int activitiesIndex = -1;

    public void populate() {
        //Created arraylist of activity objects
        //activity pool - constructs and adds new objects of the activity class
        activities.add(new Activity("Go for a hike", 0, false, "nature"));
        activities.add(new Activity("Have a picnic in the park", 6, false, "nature"));
        activities.add(new Activity("Go birdwatching", 0, false, "nature"));
        activities.add(new Activity("Go for a hike along a local trail/mountain", 0, false, "nature"));
        activities.add(new Activity("Plant a flower", 10, true, "nature"));
        activities.add(new Activity("Draw a scenic image from outside", 0, true, "nature"));
        activities.add(new Activity("Make your own nature portfolio", 150, false, "nature"));
        activities.add(new Activity("Plant a new houseplant, such as a money tree!", 15, true, "nature"));
        activities.add(new Activity("Skip rocks at a local pond/lake", 0, false, "nature"));
        activities.add(new Activity("Go to the beach", 0, false, "nature"));

        activities.add(new Activity("Listen to music on Spotify", 0, true, "music"));
        activities.add(new Activity("Go to a concert", 100, false, "music"));
        activities.add(new Activity("Play an instrument", 0, true, "music"));
        activities.add(new Activity("Sing karaoke with friends", 0, true, "music"));
        activities.add(new Activity("Write your own song", 0, true, "music"));
        activities.add(new Activity("Go to a concert at a reduced price by powering the stage on a bike", 50, false, "music"));
        activities.add(new Activity("Try listening to music in other languages", 0, true, "music"));
        activities.add(new Activity("Learn a new instrument", 50, false, "music"));
        activities.add(new Activity("Try combining everyday songs to create music", 0, true, "music"));
        activities.add(new Activity("Discover your favorite classical song", 0, true, "music"));
        activities.add(new Activity("Try listening to a different music genre", 0, true, "music"));
        activities.add(new Activity("Listen to street artists perform", 0, false, "music"));


        activities.add(new Activity("Order pizza", 12, true, "food"));
        activities.add(new Activity("Bake Cookies", 0, true, "food"));
        activities.add(new Activity("Go out to a nice restaurant", 20, false, "food"));
        activities.add(new Activity("Doordash your favorite fast food", 25, true, "food"));
        activities.add(new Activity("Find your favorite seasonal dish", 12, false, "food"));
        activities.add(new Activity("Learn how to bake", 15, true, "food"));
        activities.add(new Activity("Volunteer at a food kitchen", 0, false, "food"));

        activities.add(new Activity("Play basketball", 0, false, "sports"));
        activities.add(new Activity("Play a sports video game", 0, true, "sports"));
        activities.add(new Activity("Go outside and play a game of cornhole", 0, true, "sports"));
        activities.add(new Activity("Go to the beach for an exciting game of beach volleyball", 0, false, "sports"));
        activities.add(new Activity("Play a game of pickleball with your parents", 0, false, "sports"));
        activities.add(new Activity("Phone a friend and meet at a local tennis court to rally", 0, false, "sports"));

        activities.add(new Activity("Watch a movie at a local movie theater with a loved one", 15, false, "movies"));
        activities.add(new Activity("Rewatch your favorite movie at home", 0, true, "movies"));
        activities.add(new Activity("Watch '3 Idiots'", 0, true, "movies"));
        activities.add(new Activity("Watch your favorite Series/Trilogy/Saga", 0, true, "movies"));
        activities.add(new Activity("Try out a new show", 0, true, "movies"));
        activities.add(new Activity("Rent a movie from the local library", 0, false, "movies"));

        activities.add(new Activity("Go to the mall and shop at your favorite stores", 100, false, "clothing"));
        activities.add(new Activity("Tiedye a shirt", 10, false, "clothing"));
        activities.add(new Activity("Check out Zara for its newest deals", 50, true, "clothing"));
        activities.add(new Activity("Do a fashion show with the clothes from your wardrobe", 0, true, "clothing"));
        activities.add(new Activity("Go windowshopping at the mall", 0, false, "clothing"));

    }

    public Activity getActivity (boolean wantAtHome, int mprice, boolean wantNature, boolean wantMusic, boolean wantFood, boolean wantSports, boolean wantMovies, boolean wantClothing){
        Random r = new Random();
        boolean checkAct = false;
        preferences.clear();
        //Add all the user preferences to an array
        if (wantNature) preferences.add("nature");
        if (wantMusic) preferences.add("music");
        if (wantFood) preferences.add("food");
        if (wantSports) preferences.add("sports");
        if (wantMovies) preferences.add("movies");
        if (wantClothing) preferences.add("clothing");

        Log.d("findme",preferences.toString());

        //Check against all the user inputted preferences
        while (!checkAct){
            activitiesIndex = r.nextInt(activities.size());
            Log.d("findme","im here");

            String bool = String.valueOf(preferences.contains(activities.get(activitiesIndex).getCategory()));
            Log.d("findme", String.valueOf(wantNature&&wantMusic&&wantFood&&wantSports&&wantMovies&&wantClothing));
            if (    activities.get(activitiesIndex).getAtHome() == wantAtHome
                    &&
                    (preferences.contains(activities.get(activitiesIndex).getCategory()) || (!wantNature&&!wantMusic&&!wantFood&&!wantSports&&!wantMovies&&!wantClothing))
                    &&
                    activities.get(activitiesIndex).getCost()<=mprice
                    &&
                    !dislikes.contains(activitiesIndex)
            )
            {
                Log.d("findme",bool);
                checkAct = true;
            }

        }
        clearPref();
        checkAct = false;
        return activities.get(activitiesIndex);
    }

    public void clearPref(){
        preferences.clear();
    }

    public void addDislike(){
        if (activitiesIndex != -1) dislikes.add(activities.get(activitiesIndex));
    }

    public void clearDislike(){
        dislikes.clear();
    }
}
