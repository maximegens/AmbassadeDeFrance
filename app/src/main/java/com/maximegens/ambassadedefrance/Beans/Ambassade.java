package com.maximegens.ambassadedefrance.Beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Ambassade implements Parcelable {

    private int id;
    private String nom;

    public Ambassade(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getId());
        parcel.writeString(getNom());
    }

    public static final Parcelable.Creator<Ambassade> CREATOR = new Parcelable.Creator<Ambassade>() {
        public Ambassade createFromParcel(Parcel in) {
            return new Ambassade(in);
        }

        public Ambassade[] newArray(int size) {
            return new Ambassade[size];
        }
    };

    public Ambassade(Parcel in) {
        this.id = in.readInt();
        this.nom = in.readString();
    }
}
