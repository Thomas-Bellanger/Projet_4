package com.example.mareunion;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ParticipantsAdapater extends RecyclerView.Adapter<ParticipantsViewHolder> {
    public static List<Participants> mParticipants = new ArrayList<>();

    public ParticipantsAdapater(List<Participants> items) {
        mParticipants = items;
    }

    @NonNull
    @Override
    public ParticipantsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.participants, parent, false);

        return new ParticipantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ParticipantsViewHolder holder, int position) {
        holder.updateParticipants(mParticipants.get(position));

        ParticipantsViewHolder.retirer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click", "cliqué sur " + mParticipants.get(position).getMail());
                holder.removeParticipant(mParticipants.get(position));
                getItemCount();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mParticipants.size();
    }
}


