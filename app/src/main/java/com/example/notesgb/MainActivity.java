package com.example.notesgb;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;


import android.content.res.Configuration;
import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements NotesListFragment.Controller, NotesDetailFragment.Controller {
    private final String NOTE_FRAGMENT_TAG = "NOTE_FRAGMENT_TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPref sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState()) {
            sharedPref.setNightModeState(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            sharedPref.setNightModeState(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        showListNote();
        changeFragments();

    }

    private void showListNote() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new NotesListFragment(), NOTE_FRAGMENT_TAG)
                .commit();

    }


    @Override
    public void openProfileScreen(NotesEntity notesEntity) {
        //позже переделать
        Boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape) {
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.detail_container, NotesDetailFragment.newInstance(notesEntity))
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, NotesDetailFragment.newInstance(notesEntity))
                    .commit();
        }
    }


    @Override
    public void saveResult(NotesEntity notesEntity) {
        getSupportFragmentManager().popBackStack();
        NotesListFragment notesListFragment = (NotesListFragment) getSupportFragmentManager()
                .findFragmentByTag(NOTE_FRAGMENT_TAG);
        if (notesListFragment != null) notesListFragment.addNote(notesEntity);
    }


    public void changeFragments() {
        BottomNavigationView botNavView = findViewById(R.id.bottomNavigationView);
        botNavView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(NOTE_FRAGMENT_TAG);
            if (fragment == null) {
                fragment = new NotesDetailFragment();
            }
            switch (item.getItemId()) {
                case ItemClass.ItemId.listNoteId:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment, NOTE_FRAGMENT_TAG)
                            .commit();
                    break;
                case ItemClass.ItemId.editNoteId:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new NotesDetailFragment())
                            .addToBackStack(null)
                            .commit();
                    break;
                case ItemClass.ItemId.settingsNoteId:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new SettingsFragment())
                            .addToBackStack(null)
                            .commit();
                    break;
            }

            return false;
        });

    }


}