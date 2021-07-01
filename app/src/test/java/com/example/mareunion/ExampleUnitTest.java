package com.example.mareunion;

import org.greenrobot.eventbus.EventBus;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    ApiService mApiService= DI.getApiService();
    List<Reunion> mReunions= mApiService.getReunions();
    List<Participants> mParticipants= mApiService.getParticipants();

    @Test
    public void reunionListIsEmpty(){
        assert(mReunions.size()==0);
    }

    @Test
    public void addReunionWithSuccess() {
        Reunion reunionToAdd= new Reunion("reunionNumber", "heure", "salle", "organisateur", R.color.black);
        mApiService.addReunion(reunionToAdd);
        assert (mReunions.contains(reunionToAdd));
        
    }

    @Test
    public void deletteReunionWithSuccess() {
        Reunion reunionToDelette= new Reunion("reunionNumber", "heure", "salle", "organisateur", R.color.black);
        mApiService.addReunion(reunionToDelette);
        assert(mReunions.contains(reunionToDelette));
        mApiService.deletteReunion(reunionToDelette);
        assertFalse(mReunions.contains(reunionToDelette));
    }


    @Test
    public void addParticipantWithSuccess() {
        Participants participantToAdd= new Participants("jojo");
        mApiService.addParticipant(participantToAdd);
        assert(mParticipants.contains(participantToAdd));
    }

    @Test
    public void deletteParticipantWithSuccess() {
        Participants participantToDelette= new Participants("jojo");
        mApiService.addParticipant(participantToDelette);
        assert(mParticipants.contains(participantToDelette));
        mApiService.deletteParticipant(participantToDelette);
        assertFalse (mParticipants.contains(participantToDelette));
    }
}