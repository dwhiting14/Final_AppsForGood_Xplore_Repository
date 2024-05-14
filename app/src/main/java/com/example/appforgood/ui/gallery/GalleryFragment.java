package com.example.appforgood.ui.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appforgood.R;
import com.example.appforgood.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //initializes each checkbox
        CheckBox natureCheck = (CheckBox) root.findViewById(R.id.natureCheck);
        CheckBox musicCheck = (CheckBox) root.findViewById(R.id.musicCheck);
        CheckBox foodCheck = (CheckBox) root.findViewById(R.id.foodCheck);
        CheckBox sportsCheck = (CheckBox) root.findViewById(R.id.sportsCheck);
        CheckBox moviesCheck = (CheckBox) root.findViewById(R.id.moviesCheck);
        CheckBox clothingCheck = (CheckBox) root.findViewById(R.id.clothingCheck);
        CheckBox exerciseCheck = (CheckBox) root.findViewById(R.id.exerciseCheck);
        CheckBox artCheck = (CheckBox) root.findViewById(R.id.artCheck);

        Button savepref  = root.findViewById(R.id.savepref);

        //assigns a boolean for
        SharedPreferences sp = getActivity().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        Boolean wantNature = sp.getBoolean("nature",false);
        Boolean wantMusic = sp.getBoolean("music",false);
        Boolean wantFood = sp.getBoolean("food",false);
        Boolean wantSports = sp.getBoolean("sports",false);
        Boolean wantMovies = sp.getBoolean("movies",false);
        Boolean wantClothing = sp.getBoolean("clothing",false);
        Boolean wantExercise = sp.getBoolean("exercise",false);
        Boolean wantArt = sp.getBoolean("art",false);

        //sets the checkboxes to the last saved preference when the app is opened
        natureCheck.setChecked(wantNature);
        musicCheck.setChecked(wantMusic);
        foodCheck.setChecked(wantFood);
        sportsCheck.setChecked(wantSports);
        moviesCheck.setChecked(wantMovies);
        clothingCheck.setChecked(wantClothing);
        exerciseCheck.setChecked(wantExercise);
        artCheck.setChecked(wantArt);

        SharedPreferences.Editor editor = sp.edit();
        //saves the preferences when the save button is checked
        savepref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("nature",natureCheck.isChecked());
                editor.putBoolean("music",musicCheck.isChecked());
                editor.putBoolean("food",foodCheck.isChecked());
                editor.putBoolean("sports",sportsCheck.isChecked());
                editor.putBoolean("movies",moviesCheck.isChecked());
                editor.putBoolean("clothing",clothingCheck.isChecked());
                editor.putBoolean("exercise",exerciseCheck.isChecked());
                editor.putBoolean("art",artCheck.isChecked());
                editor.apply();
            }
        });

        /*
        natureCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("nature",isChecked);
                editor.apply();
            }
        });


        musicCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("music",isChecked);
                editor.apply();
            }
        });

        foodCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("food",isChecked);
                editor.apply();
            }
        });

        sportsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("sports",isChecked);
                editor.apply();
            }
        });

        moviesCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("movies",isChecked);
                editor.apply();
            }
        });

        clothingCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("clothing",isChecked);
                editor.apply();
            }
        });


         */
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}