package com.example.mychords;

public class Chord {
    private int note;
    private String quality = "";

    Chord(int note, String quality){
        this.note = note;
        this.quality = quality;
    }

    private void setNote(int note){
        note = this.note;
    }

    private void setQuality(String quality){
        this.quality = quality;
    }

    public int getNote() {
        return note;
    }

    public String getQuality(){
        return quality;
    }
}
