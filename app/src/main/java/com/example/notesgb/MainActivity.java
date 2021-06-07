package com.example.notesgb;

import androidx.appcompat.app.AppCompatActivity;


import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentNotesList.Controller, FragmentNotesDetail.Controller {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentNotesList fragmentNotesList = new FragmentNotesList();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragmentNotesList)
                .commit();


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
}