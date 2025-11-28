package com.example.week3_2.model;

import java.io.Serializable;

public class NotesModel implements Serializable {
    private int IdNotes;
    private String NameNotes;

    public NotesModel(int idNotes, String nameNotes) {
        this.IdNotes = idNotes;
        this.NameNotes = nameNotes;
    }

    public int getIdNotes() {
        return IdNotes;
    }
    public void setIdNotes(int idNotes) {
        this.IdNotes = idNotes;
    }
    public String getNameNotes() {
        return NameNotes;
    }
    public void setNameNotes(String nameNotes) {
        this.NameNotes = nameNotes;
    }

}
