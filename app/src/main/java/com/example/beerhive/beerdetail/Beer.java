package com.example.beerhive.beerdetail;

import android.os.Parcel;
import android.os.Parcelable;

public class Beer implements Parcelable {

    private String beerName;
    private String beerDescription;
    private String beerBrewerTips;
    private String beerImageUrl;

    String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getBeerDescription() {
        return beerDescription;
    }

    public void setBeerDescription(String beerDescription) {
        this.beerDescription = beerDescription;
    }

    public String getBeerBrewerTips() {
        return beerBrewerTips;
    }

    public void setBeerBrewerTips(String beerBrewerTips) {
        this.beerBrewerTips = beerBrewerTips;
    }

    public String getBeerImageUrl() {
        return beerImageUrl;
    }

    public void setBeerImageUrl(String beerImageUrl) {
        this.beerImageUrl = beerImageUrl;
    }

    public static Parcelable.Creator<Beer> getCREATOR() {
        return CREATOR;
    }

    public Beer(){

    }
    protected Beer(Parcel in) {
        beerName = in.readString();
        beerDescription = in.readString();
        beerBrewerTips = in.readString();
        beerImageUrl = in.readString();
    }

    public static final Parcelable.Creator<Beer> CREATOR = new Parcelable.Creator<Beer>() {
        @Override
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        @Override
        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(beerName);
        dest.writeString(beerDescription);
        dest.writeString(beerBrewerTips);
        dest.writeString(beerImageUrl);
    }
}
