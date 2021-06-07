package com.example.notesgb;

import android.os.Parcel;
import android.os.Parcelable;

public class NotesEntity implements Parcelable {
    private String noteName;
    private String noteDescription;

    public NotesEntity(String noteName, String noteDescription) {
        this.noteName = noteName;
        this.noteDescription = noteDescription;
    }

    protected NotesEntity(Parcel in) {
        noteName = in.readString();
        noteDescription = in.readString();
    }

    public String getNoteName() {
        return noteName;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    @Override
    public String toString() {
        return noteName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noteName);
        dest.writeString(noteDescription);
    }

    public static final Creator<NotesEntity> CREATOR = new Creator<NotesEntity>() {
        @Override
        public NotesEntity createFromParcel(Parcel in) {
            return new NotesEntity(in);
        }

        @Override
        public NotesEntity[] newArray(int size) {
            return new NotesEntity[size];
        }
    };
}
