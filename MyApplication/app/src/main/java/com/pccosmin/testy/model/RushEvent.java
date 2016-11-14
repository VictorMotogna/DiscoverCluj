package com.pccosmin.testy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RushEvent implements Parcelable{
    private int eventId;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private double eventLatitude;
    private double eventLongitude;
    private boolean isOnCampus;
    private String eventDescription;

    public RushEvent(int eventId, String eventName, String eventDate, String eventTime, String eventLocation, double eventLatitude, double eventLongitude, boolean isOnCampus, String eventDescription) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.eventLatitude = eventLatitude;
        this.eventLongitude = eventLongitude;
        this.isOnCampus = isOnCampus;
        this.eventDescription = eventDescription;
    }

    protected RushEvent(Parcel in) {
        eventId = in.readInt();
        eventName = in.readString();
        eventLocation = in.readString();
        eventDescription = in.readString();
        eventDate = in.readString();
        eventTime = in.readString();
        eventLatitude = in.readDouble();
        eventLongitude = in.readDouble();
        isOnCampus = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(eventId);
        dest.writeString(eventName);
        dest.writeString(eventDate);
        dest.writeString(eventTime);
        dest.writeString(eventDescription);
        dest.writeString(eventLocation);
        dest.writeDouble(eventLatitude);
        dest.writeDouble(eventLongitude);
        dest.writeByte((byte) (isOnCampus ? 1:0));
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public boolean isOnCampus() {
        return isOnCampus;
    }

    public double getEventLongitude() {
        return eventLongitude;
    }

    public double getEventLatitude() {
        return eventLatitude;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public int getEventId() {
        return eventId;
    }

    public static final Creator<RushEvent> CREATOR = new Creator<RushEvent>() {
        @Override
        public RushEvent createFromParcel(Parcel in) {
            return new RushEvent(in);
        }

        @Override
        public RushEvent[] newArray(int size) {
            return new RushEvent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
