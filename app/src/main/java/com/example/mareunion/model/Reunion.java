package com.example.mareunion.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mareunion.R;

public class Reunion implements Parcelable {
    public static final Creator<Reunion> CREATOR = new Creator<Reunion>() {
        @Override
        public Reunion createFromParcel(Parcel in) {
            return new Reunion(in);
        }

        @Override
        public Reunion[] newArray(int size) {
            return new Reunion[size];
        }
    };
    private String organisateur;
    private String reunionNumber;
    private String heure;
    private String salle;
    private int color;

    public Reunion(String reunionNumber, String heure, String salle, String organisateur) {

        this.reunionNumber = reunionNumber;
        this.heure = heure;
        this.salle = salle;
        this.organisateur = organisateur;

    }

    protected Reunion(Parcel in) {
        organisateur = in.readString();
        reunionNumber = in.readString();
        salle = in.readString();
        heure = in.readString();
        color = in.readInt();
    }

    public String getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    public String getReunionNumber() {
        return reunionNumber;
    }

    public void setReunionNumber(String reunionNumber) {
        this.reunionNumber = reunionNumber;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public int getColor() {
        return color;
    }

    public int setColor() {
        if (getSalle().equals("Peach")) {
            color = R.color.pink;

        } else if ("Mario".equals(getSalle())) {
            color = R.color.red;

        } else if ("Luigi".equals(getSalle())) {
            color = R.color.green;

        } else if ("Yoshi".equals(getSalle())) {
            color = R.color.caraibe;

        } else if ("Warrio".equals(getSalle())) {
            color = R.color.yellow;

        } else if ("Waluigi".equals(getSalle())) {
            color = R.color.purple;

        } else if ("Toad".equals(getSalle())) {
            color = R.color.grey;

        } else if ("Bowser".equals(getSalle())) {
            color = R.color.orange;

        } else if ("Étoile".equals(getSalle())) {
            color = R.color.blue;

        } else if ("Cupa".equals(getSalle())) {
            color = R.color.marron;
        }
        return color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(organisateur);
        dest.writeString(reunionNumber);
        dest.writeString(heure);
        dest.writeString(salle);
        dest.writeInt(color);
    }
}
