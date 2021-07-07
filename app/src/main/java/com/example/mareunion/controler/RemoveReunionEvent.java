package com.example.mareunion.controler;

import com.example.mareunion.model.Reunion;

public class RemoveReunionEvent {

    public Reunion reunion;

    public RemoveReunionEvent(Reunion reunion) {
        this.reunion = reunion;
    }
}