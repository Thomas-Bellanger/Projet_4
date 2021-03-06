package com.example.mareunion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.mareunion.apiService.DI;
import com.example.mareunion.apiService.DummyApiService;
import com.example.mareunion.model.Participants;
import com.example.mareunion.model.Reunion;

import org.junit.Test;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ListTest {
    DummyApiService mApiService = DI.getApiService();
    List<Reunion> mReunions = mApiService.getReunions();
    List<Participants> mParticipants = mApiService.getParticipants();

    @Test
    public void addReunionWithSuccess() {
        Reunion reunionToAdd = new Reunion("reunionNumber", "01/01/21", "heure", "salle", "organisateur");
        mApiService.addReunion(reunionToAdd);
        assertTrue(mReunions.contains(reunionToAdd));

    }

    @Test
    public void deletteReunionWithSuccess() {
        Reunion reunionToDelette = new Reunion("reunionNumber", "01/01/21", "heure", "salle", "organisateur");
        mApiService.addReunion(reunionToDelette);
        assertTrue(mReunions.contains(reunionToDelette));
        mApiService.deletteReunion(reunionToDelette);
        assertFalse(mReunions.contains(reunionToDelette));
    }


    @Test
    public void addParticipantWithSuccess() {
        Participants participantToAdd = new Participants("jojo");
        mApiService.addParticipant(participantToAdd);
        assertTrue(mParticipants.contains(participantToAdd));
    }

    @Test
    public void deletteParticipantWithSuccess() {
        Participants participantToDelette = new Participants("jojo");
        mApiService.addParticipant(participantToDelette);
        assertTrue(mParticipants.contains(participantToDelette));
        mApiService.deletteParticipant(participantToDelette);
        assertFalse(mParticipants.contains(participantToDelette));
    }

    @Test
    public void filtreList() {
        Reunion reunionToFiltre = new Reunion("reunionNumber 0", "01/01/21", "11:00", "salle", "organisateur");
        mApiService.addReunion(reunionToFiltre);
        assertTrue(mReunions.contains(reunionToFiltre));
        Reunion reunionToIgnore = new Reunion("reunionNumber 1", "01/01/21", "heure", "salle", "organisateur");
        mApiService.addReunion(reunionToIgnore);
        assertTrue(mReunions.contains(reunionToIgnore));
        assertTrue(mApiService.filterReunion("0").contains(reunionToFiltre));
    }
}