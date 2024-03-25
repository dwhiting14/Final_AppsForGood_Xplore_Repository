        package com.example.appforgood;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

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
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);




        ArrayList<ArrayList> categories = new ArrayList<>();
        ArrayList<String> nature = new ArrayList<>();
        ArrayList<String> music = new ArrayList<>();
        ArrayList<String> sports = new ArrayList<>();

        categories.add(nature);
        categories.add(music);
        categories.add(nature);
        nature.add("Go for a hike"); nature.add("Have a picnic in the park"); nature.add("Go birdwatching");
        music.add("Go to a concert"); music.add("Listen to music on spotify"); music.add("Sing karaoke with friends");
        sports.add("Play basketball"); sports.add("Play ping pong"); sports.add("Play 2k");


        //Find button
        Button btn = findViewById(R.id.clickforidea);
        //Set button behavior
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("firstapp", "CLICKED - GIVE IDEA");

                Random r = new Random();
                int catIndex = r.nextInt(categories.size());
                ArrayList<String> selectedCat = new ArrayList<>();
                selectedCat = categories.get(catIndex);

                int actIndex = r.nextInt(selectedCat.size());
                String activity = selectedCat.get(actIndex);

                Toast.makeText(MainActivity.this, activity, Toast.LENGTH_LONG).show();
            }
        });

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