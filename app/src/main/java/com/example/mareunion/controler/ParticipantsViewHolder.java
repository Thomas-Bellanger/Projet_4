package com.example.mareunion.controler;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mareunion.R;
import com.example.mareunion.model.Participants;

import org.greenrobot.eventbus.EventBus;

public class ParticipantsViewHolder extends RecyclerView.ViewHolder {
    public ImageButton retirer;
    public TextView textView;

    public ParticipantsViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.mail);
        retirer = itemView.findViewById(R.id.retirer_participant);

    }

    public void updateParticipants(Participants participant) {
        this.textView.setText(participant.getMail());

        retirer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new RetirerParticipantEvent(participant));
            }
        });
    }
}
