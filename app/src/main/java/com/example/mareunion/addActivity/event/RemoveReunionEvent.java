package com.example.mareunion.addActivity.event;

import com.example.mareunion.model.Reunion;

public class RemoveReunionEvent {

    public Reunion reunion;

    public RemoveReunionEvent(Reunion reunion) {
        this.reunion = reunion;
    }
}