package com.example.appforgood.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.content.SharedPreferences;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appforgood.Activity;
import com.example.appforgood.Controller;
import com.example.appforgood.R;
import com.example.appforgood.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment {

    int activitiesIndex = 0;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        final Controller aController = (Controller) getContext().getApplicationContext();

        aController.readData();

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

        ArrayList dislikes = new ArrayList<>();


        //SharedPreferences sp = getApplicationContext().getSharedPreferences("NaturePref", Context.MODE_PRIVATE);
        SharedPreferences sp = getActivity().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        Boolean wantNature = sp.getBoolean("nature",false);
        Boolean wantMusic = sp.getBoolean("music",false);
        Boolean wantFood = sp.getBoolean("food",false);
        Boolean wantSports = sp.getBoolean("sports",false);
        Boolean wantMovies = sp.getBoolean("movies",false);
        Boolean wantClothing = sp.getBoolean("clothing",false);
        Boolean wantExercise = sp.getBoolean("exercise",false);
        Boolean wantArt = sp.getBoolean("art",false);

        ImageView img =root.findViewById(R.id.imageView4);

        //Find button
        Button btn = root.findViewById(R.id.clickforidea);
        //Set button behavior
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("firstapp", "CLICKED - GIVE IDEA");
                TextView display = root.findViewById(R.id.text_home);
                boolean wantAtHome = homeswitch.isChecked();
                int mPrice = priceRange.getProgress();

                    Activity generated = aController.getActivity(wantAtHome, mPrice, wantNature, wantMusic, wantFood, wantSports, wantMovies, wantClothing,wantExercise,wantArt);

                    String displayAtHome = "This activity is not at home";
                    if (generated.getAtHome()) {
                        displayAtHome = "This activity is at home";
                    }
                    display.setText(
                            generated.getName() + "\n" + "Price = $" + generated.getCost() + "\n" + displayAtHome + "\n" + "Category: " + generated.getCategory());

                    int imageResource = getResources().getIdentifier(generated.getPic(), "drawable", getActivity().getPackageName());
                    img.setImageResource(imageResource);

            }


        });


        Button dislike = root.findViewById(R.id.dislike);
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aController.addDislike();
            }
        });

        Button cleardislikes = root.findViewById(R.id.cleardislikes);
        cleardislikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aController.clearDislike();
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