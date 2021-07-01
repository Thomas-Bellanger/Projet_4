package com.example.mareunion;

import java.util.ArrayList;
import java.util.List;

public class ApiService {
    public List<Reunion> reunions = new ArrayList<>();
    public List<Participants> participants= new ArrayList<>();


    public List<Reunion> getReunions(){return reunions;}

    void deletteReunion(Reunion reunion){reunions.remove(reunion);}

    void addReunion(Reunion reunion){reunions.add(reunion);}

    public List<Participants> getParticipants(){return participants;}

    void addParticipant(Participants participant){participants.add(participant);}

    void deletteParticipant(Participants participant){participants.remove(participant);}

}
