package com.example.mareunion.controler;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mareunion.R;
import com.example.mareunion.controler.RemoveReunionEvent;
import com.example.mareunion.model.Reunion;

import org.greenrobot.eventbus.EventBus;


public class ReunionListViewHolder extends RecyclerView.ViewHolder {
    public final ImageView image;
    public final TextView reunionNumber;
    public final TextView heure;
    public final TextView salle;
    public final TextView organisateur;
    public final ImageButton delette;
    int color;
    int indiceColor;


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
        getTint();
        this.organisateur.setText(reunion.getOrganisateur());
        this.reunionNumber.setText(reunion.getReunionNumber());
        this.heure.setText(reunion.getHeure());
        this.salle.setText(reunion.getSalle());
        Glide.with(image.getContext())
                .load(R.drawable.baseline_info_black_24)
                .apply(RequestOptions.circleCropTransform())
                .into(image);
        image.setColorFilter(color);

    }

    public void deletteReunion(Reunion reunion) {
        EventBus.getDefault().post(new RemoveReunionEvent(reunion));
    }

    void getTint(){
        if (indiceColor==0){
            color = R.color.pink;
            indiceColor=1;
        }
        if (indiceColor==1){
            color = (R.color.red);
            indiceColor=2;
        }
        if (indiceColor==2){
            color = (R.color.green);
            indiceColor=3;
        }
        if (indiceColor==3){
            color = (R.color.yellow);
            indiceColor=4;
        }
        if (indiceColor==4){
            color = (R.color.orange);
            indiceColor=5;
        }
        if (indiceColor==5){
            color = (R.color.purple);
            indiceColor=0;
        }
    }
}
