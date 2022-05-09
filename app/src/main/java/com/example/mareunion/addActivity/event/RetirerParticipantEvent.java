package com.example.mareunion.addActivity.event;

import com.example.mareunion.model.Participants;

public class RetirerParticipantEvent {


    public Participants participant;


    public RetirerParticipantEvent(Participants participant) {
        this.participant = participant;
    }
}



