package com.example.notesgb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;


public class SettingsFragment extends Fragment {

    private Switch themeSwitcher;
    private SharedPref sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        sharedPref = new SharedPref(getContext());
        themeSwitcher = view.findViewById(R.id.switch_theme);

        if (sharedPref.loadNightModeState()) {
            themeSwitcher.setChecked(true);
        }

        themeSwitcher.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (themeSwitcher.isChecked()) {
                sharedPref.setNightModeState(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                themeSwitcher.setChecked(true);

            } else {
                sharedPref.setNightModeState(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                themeSwitcher.setChecked(false);

            }
        });
    }
}