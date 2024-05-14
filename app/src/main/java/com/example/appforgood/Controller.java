package com.example.appforgood;

import android.app.Application;
import android.renderscript.ScriptGroup;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Controller extends Application {

    //Initializes Data: an array of activities, an array of preferences, and an array of dislikes. It also sets an initial index of the activites array
    ArrayList<Activity> activities = new ArrayList<>();
    ArrayList preferences = new ArrayList();
    ArrayList dislikes = new ArrayList();
    int activitiesIndex = -1;
    int counter = -1;

    //PARAM: if the user wants to stay at home or not
    //PARAM: The user's max price
    //PARAM: If the user likes nature activities
    //PARAM: If the user likes music activities
    //PARAM: If the user likes food activities
    //PARAM: If the user likes sports activities
    //PARAM: If the user likes movie activities
    //PARAM: If the user likes clothing activities
    //PARAM: If the user likes exercise activities
    //PARAM: If the user likes art activities
    //RETURNS: an object of the activity class that matches the user inputted parameters
    public Activity getActivity (boolean wantAtHome, double mprice, boolean wantNature, boolean wantMusic, boolean wantFood, boolean wantSports, boolean wantMovies, boolean wantClothing, boolean wantExercise, boolean wantArt){
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
        if (wantExercise) preferences.add("exercise");
        if (wantArt) preferences.add("art");

        //checks to see if at least one activity fits their parameter
        Log.d("findme",preferences.toString());
        Boolean somethingWorks = false;
        for (int index = 0; index <activities.size(); index++){
            if (    activities.get(index).getAtHome() == wantAtHome
                    &&
                    (preferences.contains(activities.get(index).getCategory()) || (!wantNature&&!wantMusic&&!wantFood&&!wantSports&&!wantMovies&&!wantClothing&&!wantExercise&&!wantArt))
                    &&
                    activities.get(index).getCost()<=mprice
                    &&
                    !dislikes.contains(index)
            ){
                somethingWorks = true;
            }
        }
        if (somethingWorks){
            //Check against all the user inputted preferences
            while (!checkAct){
                activitiesIndex = r.nextInt(activities.size());
                String bool = String.valueOf(preferences.contains(activities.get(activitiesIndex).getCategory()));
                if (    activities.get(activitiesIndex).getAtHome() == wantAtHome
                        &&
                        (preferences.contains(activities.get(activitiesIndex).getCategory()) || (!wantNature&&!wantMusic&&!wantFood&&!wantSports&&!wantMovies&&!wantClothing&&!wantExercise&&!wantArt))
                        &&
                        activities.get(activitiesIndex).getCost()<=mprice
                        &&
                        !dislikes.contains(activitiesIndex)
                )
                {
                    Log.d("findme", String.valueOf(activitiesIndex));
                    checkAct = true;
                }

            }
            clearPref();
            checkAct = false;
            return activities.get(activitiesIndex);
        }
        else {
            clearPref();
            checkAct = false;
            return  activities.get(0);
        }
    }

    //clears the preferences array
    public void clearPref(){
        preferences.clear();
    }

    //adds the last generated activty to the dislike array even if there is a duplicate
    public void addDislike(){
        int currentI=activitiesIndex;
        for (int index = 0; index<activities.size(); index++){
            if (activities.get(index).getName().equals(activities.get(activitiesIndex).getName()) && !activities.contains(index)) dislikes.add(index);
        }

    }
    //clears the dislike array
    public void clearDislike(){
        dislikes.clear();
    }

    //Reads the CSV file appdata and adds each line as a new object of the activity class to the activity array
    public void readData() {
        Log.d("readdata","data read");

        InputStream is = getResources().openRawResource(R.raw.appdata);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = "";

        try {
            while ((line = reader.readLine()) != null){
                //split by comma
                String[] data = line.split(",");
                activities.add(new Activity(data[0], Double.parseDouble(data[1]), Boolean.parseBoolean(data[2]), data[3],data[4]));

            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Activity a:activities){
            Log.d("populate",a.getName());
        }
    }
}
