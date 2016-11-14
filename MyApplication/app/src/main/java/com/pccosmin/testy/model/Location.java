package com.pccosmin.testy.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CristianCosmin on 09.11.2016.
 */

public class Location implements Parcelable {
    private int locationId;
    private String locationName;
    private String aboutText;
    private String locationPicture;
    private String address;
    private String inauguration;
    private String funFact;
    private double latitude;
    private double longitude;

    public Location(int locationId, String locationName, String aboutText, String locationPicture, String address, String inauguration, String funFact, double latitude, double longitude) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.aboutText = aboutText;
        this.locationPicture = locationPicture;
        this.address = address;
        this.inauguration = inauguration;
        this.funFact = funFact;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(locationId);
        dest.writeString(locationName);
        dest.writeString(aboutText);
        dest.writeString(locationPicture);
        dest.writeString(address);
        dest.writeString(inauguration);
        dest.writeString(funFact);
    }

    public Location(Parcel in){
        locationId = in.readInt();
        locationName = in.readString();
        aboutText = in.readString();
        locationPicture = in.readString();
        address = in.readString();
        inauguration = in.readString();
        funFact = in.readString();
    }

    public int getLocationId() {
        return locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getAboutText() {
        return aboutText;
    }

    public String getLocationPicture() {
        return locationPicture;
    }

    public String getAddress() {
        return address;
    }

    public String getInauguration() {
        return inauguration;
    }

    public String getFunFact() {
        return funFact;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
