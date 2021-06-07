package com.example.notesgb;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentNotesDetail extends Fragment {
    private static final String NOTES_KEY = "NOTES_KEY";
    private EditText notesName;
    private NotesEntity notesEntity = null;
    private EditText notesDescription;
    private Button saveNoteBtn;

    public static FragmentNotesDetail newInstance(NotesEntity notesEntity) {
        FragmentNotesDetail fragmentNotesDetail = new FragmentNotesDetail();
        Bundle args = new Bundle();

        args.putParcelable(NOTES_KEY, notesEntity);

        fragmentNotesDetail.setArguments(args);
        return fragmentNotesDetail;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_details, null);
        notesName = view.findViewById(R.id.edit_note_name);
        notesDescription = view.findViewById(R.id.edit_note_description);
        saveNoteBtn = view.findViewById(R.id.btn_note_save);

        saveNoteBtn.setOnClickListener(v -> {
            Controller controller = (Controller) getActivity();
            controller.saveResult(new NotesEntity(notesName.getText().toString(),
                    notesDescription.getText().toString()));


        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        notesName.setText(notesEntity.getNoteName());
        notesDescription.setText(notesEntity.getNoteDescription());


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Controller)) {
            throw new RuntimeException("Activity must implement FragmentNotes.Detail.Controller");
        }
        if (getArguments() != null) {
            notesEntity = getArguments().getParcelable(NOTES_KEY);
        }
    }

    public interface Controller {
        void saveResult(NotesEntity notesEntity);
    }
}
