package com.example.notesgb;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements FragmentNotesList.Controller, FragmentNotesDetail.Controller {
    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState()) {
            sharedPref.setNightModeState(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            sharedPref.setNightModeState(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        FragmentNotesList fragmentNotesList = new FragmentNotesList();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragmentNotesList)
                .commit();
        changeFragments();

    }

    @Override
    public void openProfileScreen(NotesEntity notesEntity) {
        Boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, FragmentNotesDetail.newInstance(notesEntity))
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, FragmentNotesDetail.newInstance(notesEntity))
                    .addToBackStack(null)
                    .commit();
        }
    }


    @Override
    public void saveResult(NotesEntity notesEntity) {
        //todo
    }

    public void changeFragments() {
        BottomNavigationView botNavView = findViewById(R.id.bottomNavigationView);
        botNavView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.list_item:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new FragmentNotesList())
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.add_item:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new AddNoteFragment())
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.settings:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new SettingsFragment())
                            .addToBackStack(null)
                            .commit();

            }

            return false;
        });

    }

}