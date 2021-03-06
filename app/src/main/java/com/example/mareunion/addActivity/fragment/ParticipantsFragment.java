package com.example.mareunion.addActivity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareunion.R;
import com.example.mareunion.addActivity.adapter.ParticipantsAdapater;
import com.example.mareunion.model.Participants;

import java.util.List;


public class ParticipantsFragment extends Fragment {
    RecyclerView mRecyclerView;
    List<Participants> mParticipants;
    private ParticipantsAdapater mAdapater;


    public ParticipantsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_participants, container, false);
        this.configureRecyclerView();
        return view;
    }

    private void configureRecyclerView() {
        mAdapater = new ParticipantsAdapater(mParticipants);
        mRecyclerView.setAdapter(mAdapater);
    }
}