package com.example.mareunion;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void reunionListIsEmpty(){
        List<Reunion> reunions = ReunionListViewAdapter.mReunions;
        assert(reunions.isEmpty());
    }

    @Test
    public void participantListIsempty() {
        List<Participants> participantsTest= ApiService.getMParticipants();
        assert(participantsTest.isEmpty());
    }
    @Test
    public void addReunionWithSuccess() {
        Reunion reunionToAdd= new Reunion("reunionNumber", "heure", "salle", "organisateur", "avatar");
        ApiService.addReunion(reunionToAdd);
        assert(ApiService.reunions.contains(reunionToAdd));
        
    }

    @Test
    public void deletteReunionWithSuccess() {
        Reunion reunionToDelette= new Reunion("reunionNumber", "heure", "salle", "organisateur", "avatar");
        ApiService.addReunion(reunionToDelette);
        assert(ApiService.reunions.contains(reunionToDelette));
        ApiService.deleteReunion(reunionToDelette);
        assertFalse(ApiService.reunions.contains(reunionToDelette));
    }


    @Test
    public void addParticipantWithSuccess() {
        Participants participantToAdd= new Participants("jojo");
        ApiService.addParticipant(participantToAdd);
        assert(ApiService.participants.contains(participantToAdd));
    }

    @Test
    public void deletteParticipantWithSuccess() {
        Participants participantToDelette= new Participants("jojo");
        ApiService.addParticipant(participantToDelette);
        assert(ApiService.participants.contains(participantToDelette));
        ApiService.removeParticipant(participantToDelette);
        assertFalse (ApiService.participants.contains(participantToDelette));
    }
}