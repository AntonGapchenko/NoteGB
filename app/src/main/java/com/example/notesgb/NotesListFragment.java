package com.example.notesgb;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class NotesListFragment extends Fragment {
    private ListView notesListView;
    private ArrayList<NotesEntity> notes = new ArrayList<>();
    private ArrayAdapter<NotesEntity> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        notesListView = view.findViewById(R.id.notes_list);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, notes);
        notesListView.setAdapter(adapter);
        notesListView.setOnItemClickListener((parent, view1, position, id) -> {
            NotesEntity notesEntity = notes.get(position);
            getController().openProfileScreen(notesEntity);


        });

    }

    public void addNote(NotesEntity note) {
        NotesEntity sameNote = findNoteWithId(note.getId());
        if (sameNote != null) {
            notes.remove(sameNote);
        }
        notes.add(note);

    }

    private NotesEntity findNoteWithId(String id) {
        for (NotesEntity note : notes) {
            if (note.getId().equals(id)) {
                return note;
            }

        }
        return null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Controller)) {
            throw new RuntimeException("Activity must implement FragmentNotesList.Controller");
        }
    }


    private Controller getController() {
        return (Controller) getActivity();
    }

    interface Controller {
        void openProfileScreen(NotesEntity notesEntity);

    }

}
