        package com.example.appforgood;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.SeekBar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appforgood.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    Switch homeswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();

            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

/*

        //Created arraylist of activity objects
        ArrayList<Activity> activities = new ArrayList<>();

        //constructs and adds new objects of the activity class
        activities.add(new Activity("Go for a hike", 0, false, "nature"));
        activities.add(new Activity("Have a picnic in the park", 6, false, "nature"));
        activities.add(new Activity("Go birdwatching", 0, false, "nature"));

        activities.add(new Activity("Listen to music on Spotify", 0, true, "music"));
        activities.add(new Activity("Go to a concert", 100, false, "music"));
        activities.add(new Activity("Play an instrument",0,true,"music"));
        activities.add(new Activity("Sing karaoke with friendst",0,true,"music"));

        activities.add(new Activity("Order pizza", 12, true, "food"));
        activities.add(new Activity("Bake Cookies", 0, true, "food"));
        activities.add(new Activity("Go out to a nice restaurant", 20, true, "food"));

        activities.add(new Activity("Play basketball", 0, false, "sports"));
        activities.add(new Activity("Play a sports video game", 0, true, "sports"));


        ArrayList preferences = new ArrayList();



        homeswitch = (Switch) findViewById(R.id.switch1);

        SeekBar priceRange = (SeekBar)findViewById(R.id.priceRange);
        TextView displayPrice = (TextView)findViewById(R.id.displayPrice);

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


        SharedPreferences natureC = getApplicationContext().getSharedPreferences("NaturePref", Context.MODE_PRIVATE);
        Boolean wantNature = natureC.getBoolean("nature",true);


        //Find button
        Button btn = findViewById(R.id.clickforidea);
        //Set button behavior
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("firstapp", "CLICKED - GIVE IDEA");

                Random r = new Random();
                boolean wantAtHome = homeswitch.isChecked();
                Boolean checkAct = false;
                int activitiesIndex = 0;

                //Add all the user prefrences to an array
                //ADD FOR EACH CATEGORY
                if (wantNature)
                    preferences.add("nature");


                //Check against all the user inputted preferences
                //Add more && to the if statement under the while to check mor preferences one we have the buttons
                while (!checkAct){
                    activitiesIndex = r.nextInt(activities.size());
                    int mPrice= priceRange.getProgress();


                    if (activities.get(activitiesIndex).getAtHome() == wantAtHome
                        &&
                            preferences.contains(activities.get(activitiesIndex).getCategory())){ //DOES NOT WORK
                        checkAct = true;
                    }
                }
                //Toast.makeText(getApplicationContext(),"Im here", Toast.LENGTH_LONG).show();


                TextView display = findViewById(R.id.text_home);
                String displayAtHome = "This activity is not at home";
                if (activities.get(activitiesIndex).getAtHome()){
                    displayAtHome = "This activity is at home";
                }
                display.setText(activities.get(activitiesIndex).getName() + "\n" + "Price = $" + activities.get(activitiesIndex).getCost() + "\n" + displayAtHome + "\n" + "Category: " + activities.get(activitiesIndex).getCategory());

            }
        });
*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}