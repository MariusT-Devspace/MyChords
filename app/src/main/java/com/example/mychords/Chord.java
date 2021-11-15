package com.example.mychords;

public class Chord {
    private int notePosition;
    private String note;
    private String quality = "";

    Chord(int notePosition, String note, String quality){
        this.notePosition = notePosition;
        this.note = note;
        this.quality = quality;
    }

    protected void setNote(int note){
        this.notePosition = note;
    }

    protected void setQuality(String quality){
        this.quality = quality;
    }

    protected int getNotePosition() {
        return notePosition;
    }

    protected String getQuality(){
        return quality;
    }

    protected String getChord(){return (note + quality);}
}
