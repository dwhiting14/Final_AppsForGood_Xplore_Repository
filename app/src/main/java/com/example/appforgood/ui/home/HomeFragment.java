package com.example.appforgood.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.SharedPreferences;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appforgood.databinding.FragmentHomeBinding;

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
        return root;



        //SharedPreferences nature = getActivity().getSharedPreferences("Nature", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = nature.edit();
        //editor.putInt(getString(R.string.saved_high_score_key), newHighScore);
        //editor.apply();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}