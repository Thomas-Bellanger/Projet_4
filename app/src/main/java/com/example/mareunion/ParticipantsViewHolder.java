package com.example.mareunion;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ParticipantsViewHolder extends RecyclerView.ViewHolder {
    public static ImageButton retirer;
    public TextView textView;

    public ParticipantsViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.mail);
        retirer = itemView.findViewById(R.id.retirer_participant);
    }

    public void updateParticipants(Participants participant) {
        this.textView.setText(participant.getMail());
    }

    public void removeParticipant(Participants participant) {
        ParticipantsAdapater.mParticipants.remove(participant);
    }
}
