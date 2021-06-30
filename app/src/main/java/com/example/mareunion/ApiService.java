package com.example.mareunion;

import java.util.ArrayList;
import java.util.List;

public class ApiService {

    public static List<Reunion> reunions = new ArrayList<>();

    public static List<Participants> participants = new ArrayList<>();


    public static List<Reunion> getReunions() {
        return reunions;
    }

    public static List<Participants> getParticipants() {
        return participants;
    }

    public static List<Participants> getMParticipants() {
        return ParticipantsAdapater.mParticipants;
    }

    public static void addReunion(Reunion reunion) {
        reunions.add(reunion);
    }

    public static void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }

    public static void addParticipant(Participants participant) {
        participants.add(participant);
    }

    public static void removeParticipant(Participants participant) {
        participants.remove(participant);
    }

}
