package com.example.mareunion.mainActivity.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mareunion.R;
import com.example.mareunion.addActivity.event.RemoveReunionEvent;
import com.example.mareunion.model.Reunion;

import org.greenrobot.eventbus.EventBus;


public class ReunionListViewHolder extends RecyclerView.ViewHolder {
    public final ImageView image;
    public final TextView reunionNumber;
    public final TextView heure;
    public final TextView salle;
    public final TextView organisateur;
    public final ImageButton delette;
    public int color;


    public ReunionListViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.imageView);
        reunionNumber = itemView.findViewById(R.id.reunionText);
        heure = itemView.findViewById(R.id.heureText);
        salle = itemView.findViewById(R.id.salleText);
        organisateur = itemView.findViewById(R.id.Organisateur);
        delette = itemView.findViewById(R.id.delette);

        if (salle.equals("Peach")) {
            color = R.color.pink;

        } else if ("Mario".equals(salle)) {
            color = R.color.red;

        } else if ("Luigi".equals(salle)) {
            color = R.color.green;

        } else if ("Yoshi".equals(salle)) {
            color = R.color.caraibe;

        } else if ("Warrio".equals(salle)) {
            color = R.color.yellow;

        } else if ("Waluigi".equals(salle)) {
            color = R.color.purple;

        } else if ("Toad".equals(salle)) {
            color = R.color.grey;

        } else if ("Bowser".equals(salle)) {
            color = R.color.orange;

        } else if ("Étoile".equals(salle)) {
            color = R.color.blue;

        } else if ("Cupa".equals(salle)) {
            color = R.color.marron;
        }
    }

    public void bind(Reunion reunion) {
        this.organisateur.setText(reunion.getOrganisateur().replace("[", "").replace("]", ""));
        this.reunionNumber.setText(reunion.getReunionNumber());
        this.heure.setText(reunion.getHeure());
        this.salle.setText(reunion.getSalle());
        Glide.with(image.getContext())
                .load(R.drawable.baseline_info_white_24)
                .apply(RequestOptions.circleCropTransform())
                .into(image);

        image.setColorFilter(ContextCompat.getColor(this.salle.getContext(), reunion.setColor()));
    }

    public void deletteReunion(Reunion reunion) {
        EventBus.getDefault().post(new RemoveReunionEvent(reunion));
    }
}
