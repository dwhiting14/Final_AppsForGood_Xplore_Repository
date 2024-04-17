package com.example.appforgood.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appforgood.Activity;
import com.example.appforgood.R;
import com.example.appforgood.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //Created arraylist of activity objects
        ArrayList<Activity> activities = new ArrayList<>();

        //activity pool - constructs and adds new objects of the activity class
        activities.add(new Activity("Go for a hike", 0, false, "nature"));
        activities.add(new Activity("Have a picnic in the park", 6, false, "nature"));
        activities.add(new Activity("Go birdwatching", 0, false, "nature"));
        activities.add(new Activity("Go for a hike along a local trail/mountain", 0, false, "nature"));
        activities.add(new Activity("Plant a flower", 10, true, "nature"));
        activities.add(new Activity("Draw a scenic image from outside",0, true,"nature"));
        activities.add(new Activity("Make your own nature portfolio",150, false,"nature"));
        activities.add(new Activity("Plant a new houseplant, such as a money tree!",15,true,"nature"));
        activities.add(new Activity("Skip rocks at a local pond/lake",0, false,"nature"));
        activities.add(new Activity("Go to the beach",0,false,"nature"));

        activities.add(new Activity("Listen to music on Spotify", 0, true, "music"));
        activities.add(new Activity("Go to a concert", 100, false, "music"));
        activities.add(new Activity("Play an instrument",0,true,"music"));
        activities.add(new Activity("Sing karaoke with friends",0,true,"music"));
        activities.add(new Activity("Write your own song",0, true,"music"));
        activities.add(new Activity("Go to a concert at a reduced price by powering the stage on a bike",50,false,"music"));
        activities.add(new Activity("Try listening to music in other languages",0,true,"music"));
        activities.add(new Activity("Learn a new instrument",50,false,"music"));
        activities.add(new Activity("Try combining everyday songs to create music",0	,true,"music"));
        activities.add(new Activity("Discover your favorite classical song",0,true,"music"));
        activities.add(new Activity("Try listening to a different music genre",0,true,"music"));

        activities.add(new Activity("Order pizza", 12, true, "food"));
        activities.add(new Activity("Bake Cookies", 0, true, "food"));
        activities.add(new Activity("Go out to a nice restaurant", 20, false, "food"));
        activities.add(new Activity("Doordash your favorite fast food",25,true,"food"));
        activities.add(new Activity("Find your favorite seasonal dish",12,false,"food"));
        activities.add(new Activity("Learn how to bake",15,true,"food"));

        activities.add(new Activity("Play basketball", 0, false, "sports"));
        activities.add(new Activity("Play a sports video game", 0, true, "sports"));
        activities.add(new Activity("Go outside and play a game of cornhole",0,true,"sports"));
        activities.add(new Activity("Go to the beach for an exciting game of beach volleyball",0,false,"sports"));
        activities.add(new Activity("Play a game of pickleball with your parents",0,false,"sports"));
        activities.add(new Activity("Phone a friend and meet at a local tennis court to rally",0	,false,	"sports"));

        activities.add(new Activity("Watch a movie at a local movie theater with a loved one",15, false,	"movies"));
        activities.add(new Activity("Rewatch your favorite movie at home",0,true	,"movies"));
        activities.add(new Activity("Watch '3 Idiots'",0,true,"movies"));
        activities.add(new Activity("Watch your favorite Series/Trilogy/Saga",0,true,"movies"));
        activities.add(new Activity("Try out a new show",0,true,"movies"));

        activities.add(new Activity("Go to the mall and shop at your favorite stores",100,false,"clothing"));
        activities.add(new Activity("Tiedye a shirt",10, false,"clothing"));
        activities.add(new Activity("Check out Zara for its newest deals",50,true,"clothing"));
        activities.add(new Activity("Do a fashion show with the clothes from your wardrobe",0,true,"clothing"));

        ArrayList preferences = new ArrayList();


        //homeswitch = (Switch) findViewById(R.id.switch1);

        Switch homeswitch = (Switch) root.findViewById(R.id.switch1);

        SeekBar priceRange = (SeekBar)root.findViewById(R.id.priceRange);
        TextView displayPrice = (TextView)root.findViewById(R.id.displayPrice);

        int maxPrice= priceRange.getProgress(); //DOES NOT WORK
        priceRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                displayPrice.setText("Max Price = $" + String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        //SharedPreferences sp = getApplicationContext().getSharedPreferences("NaturePref", Context.MODE_PRIVATE);
        SharedPreferences sp = getActivity().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        Boolean wantNature = sp.getBoolean("nature",false);
        Boolean wantMusic = sp.getBoolean("music",false);
        Boolean wantFood = sp.getBoolean("food",false);
        Boolean wantSports = sp.getBoolean("sports",false);



        //Find button
        Button btn = root.findViewById(R.id.clickforidea);
        //Set button behavior
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("firstapp", "CLICKED - GIVE IDEA");

                Random r = new Random();
                boolean wantAtHome = homeswitch.isChecked();
                Boolean checkAct = false;
                int mPrice= priceRange.getProgress();
                int activitiesIndex = 0;

                //Add all the user preferences to an array
                //ADD FOR EACH CATEGORY
                if (wantNature) preferences.add("nature");
                if (wantMusic) preferences.add("music");
                if (wantFood) preferences.add("food");
                if (wantSports) preferences.add("sports");


                //Check against all the user inputted preferences
                //Add more && to the if statement under the while to check mor preferences one we have the buttons
                while (!checkAct){
                    activitiesIndex = r.nextInt(activities.size());

                    if (    activities.get(activitiesIndex).getAtHome() == wantAtHome
                            &&
                            preferences.contains(activities.get(activitiesIndex).getCategory())
                            &&
                            activities.get(activitiesIndex).getCost()<=mPrice
                        )
                    {
                        checkAct = true;
                    }
                }

                TextView display = root.findViewById(R.id.text_home);
                String displayAtHome = "This activity is not at home";
                if (activities.get(activitiesIndex).getAtHome()){
                    displayAtHome = "This activity is at home";
                }
                display.setText(
                        activities.get(activitiesIndex).getName() + "\n" + "Price = $" + activities.get(activitiesIndex).getCost() + "\n" + displayAtHome + "\n" + "Category: " + activities.get(activitiesIndex).getCategory());
               // Toast.makeText(getApplicationContext(),"Im here", Toast.LENGTH_LONG).show();
               // Toast.makeText(getContext(),wantNature)
                preferences.clear();

            }
        });

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}