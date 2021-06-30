package com.example.mareunion;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class ReunionListViewHolder extends RecyclerView.ViewHolder {
    public final ImageView image;
    public final TextView reunionNumber;
    public final TextView heure;
    public final TextView salle;
    public final TextView organisateur;
    public final ImageButton delette;


    public ReunionListViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.imageView);
        reunionNumber = itemView.findViewById(R.id.reunionText);
        heure = itemView.findViewById(R.id.heureText);
        salle = itemView.findViewById(R.id.salleText);
        organisateur = itemView.findViewById(R.id.Organisateur);
        delette = itemView.findViewById(R.id.delette);
    }

    public void bind(Reunion reunion) {
        this.organisateur.setText(reunion.getOrganisateur());
        this.reunionNumber.setText(reunion.getReunionNumber());
        this.heure.setText(reunion.getHeure());
        this.salle.setText(reunion.getSalle());
        Glide.with(image.getContext())
                .load(reunion.getAvatar())
                .placeholder(android.R.drawable.ic_dialog_info)
                .apply(RequestOptions.circleCropTransform())
                .into(image);
    }

    public void deletteReunion(Reunion reunion) {
        ReunionListViewAdapter.mReunions.remove(reunion);
    }
}
