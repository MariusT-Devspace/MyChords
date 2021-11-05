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

    private void setNote(int note){
        this.notePosition = note;
    }

    private void setQuality(String quality){
        this.quality = quality;
    }

    public int getNotePosition() {
        return notePosition;
    }

    public String getQuality(){
        return quality;
    }

    public String getChord(){return (note + quality);}
}
