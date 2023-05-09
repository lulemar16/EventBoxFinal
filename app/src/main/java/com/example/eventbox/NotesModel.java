package com.example.eventbox;


public class NotesModel {

    private int id;
    private String note;

    public NotesModel(int id, String note) {
        this.id = id;
        this.note = note;
    }

    // toString

    @Override
    public String toString() {
        return  note + '\n' ;
    }


    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
