package com.example.mareunion.controler;

import android.util.Log;

import com.example.mareunion.model.Participants;
import com.example.mareunion.model.Reunion;

import java.util.ArrayList;
import java.util.List;

public class DummyApiService implements ApiService {
    public List<Reunion> reunions = new ArrayList<>();

    public int color;

    public List<Participants> participants = new ArrayList<>();

    @Override
    public List<Reunion> getReunions() {
        return reunions;
    }

    @Override
    public void deletteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }

    @Override
    public void addReunion(Reunion reunion) {

        reunions.add(reunion);
    }

    @Override
    public List<Participants> getParticipants() {
        return participants;
    }

    @Override
    public void addParticipant(Participants participant) {
        participants.add(participant);
    }

    @Override
    public void deletteParticipant(Participants participant) {
        participants.remove(participant);
    }

    @Override
    public List<Reunion> filterReunion(String filterPattern) {

        List<Reunion> filteredList = new ArrayList<>();

        if (filterPattern == null || filterPattern.length() == 0) {
            return reunions;
        } else {
            String filterLowerCase = filterPattern.toLowerCase();
            for (Reunion reunion : reunions) {
                if (reunion.getHeure().toLowerCase().contains(filterLowerCase)) {
                    filteredList.add(reunion);
                    Log.e("ajout_heure", "ajout par heure");
                } else if (reunion.getSalle().toLowerCase().contains(filterLowerCase)) {
                    filteredList.add(reunion);
                    Log.e("ajout_salle", "ajout par salle");
                } else if (reunion.getReunionNumber().toLowerCase().contains(filterLowerCase)) {
                    filteredList.add(reunion);
                    Log.e("ajout_reunion", "ajout par numéro");
                }
            }
            Log.e("list", filteredList.toString());
            return filteredList;
        }
    }
}
