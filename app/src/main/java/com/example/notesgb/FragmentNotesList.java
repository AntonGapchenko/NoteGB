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

public class FragmentNotesList extends Fragment {
    private ListView notesList;
    private ArrayList<NotesEntity> notes = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        notesList = view.findViewById(R.id.notes_list);
        addNotesToList();
        ArrayAdapter<NotesEntity> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, notes);
        notesList.setAdapter(adapter);
        notesList.setOnItemClickListener((parent, view1, position, id) -> {
            NotesEntity notesEntity = notes.get(position);
            ((Controller) getActivity()).openProfileScreen(notesEntity);


        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Controller)) {
            throw new RuntimeException("Activity must implement FragmentNotesList.Controller");
        }
    }

    private void addNotesToList() {
        notes.add(new NotesEntity("Поход в Магазин",
                "Сходить в магазин и купить продуктов"));
        notes.add(new NotesEntity("Оплата",
                "Оплатить коммунальные услуги"));
        notes.add(new NotesEntity("Документы",
                "Принести паспорт, СНИЛС"));
    }

    interface Controller {
        void openProfileScreen(NotesEntity notesEntity);
    }

}
