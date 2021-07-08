package com.example.mareunion.controler;

import com.example.mareunion.model.Participants;
import com.example.mareunion.model.Reunion;

import java.util.List;

public interface ApiService {
    List<Reunion> getReunions();

    void deletteReunion(Reunion reunion);

    void addReunion(Reunion reunion);

    List<Participants> getParticipants();

    void addParticipant(Participants participant);

    void deletteParticipant(Participants participant);

    List<Reunion> filterReunion(String filterPattern);

    List<Reunion> getFilteredList();
}
