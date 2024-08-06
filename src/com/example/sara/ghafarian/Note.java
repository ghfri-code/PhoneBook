package com.example.sara.ghafarian;

import java.util.Date;


public class Note {
    private int idnote;
    private int idcont;
    private String notetext;
    

    public Note() {
    }

    public Note(int idnote, int idcont, String notetext) {
        setIdnote(idnote);
        setIdcont(idcont);
        setNotetext(notetext);
    }

    public Note(int idcont, String notetext ) {
        setIdcont(idcont);
        setNotetext(notetext);
        
    }
    public Note(String notetext) {
        setNotetext(notetext);
    }

    public Note(int idcont) {
        setIdcont(idcont);
    }

    public String getNotetext() {
        return notetext;
    }

    public void setNotetext(String notetext) {
        this.notetext = notetext;
    }

    public int getIdcont() {
        return idcont;
    }

    public void setIdcont(int idcont) {
        this.idcont = idcont;
    }

    public int getIdnote() {
        return idnote;
    }

    public void setIdnote(int idnote) {
        this.idnote = idnote;
    }
}
