package com.pccosmin.testy.model;

public class EventCard {
    private int eventId;
    private String eventName;
    private String eventDescription;
    private String eventImage;
    private boolean isVideo;
    private String youtubeEnding;

    public EventCard(String eventName, int eventId, String eventDescription, String eventImage, boolean isVideo, String youtubeEnding) {
        this.eventName = eventName;
        this.eventId = eventId;
        this.eventDescription = eventDescription;
        this.eventImage = eventImage;
        this.isVideo = isVideo;
        this.youtubeEnding = youtubeEnding;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public String getEventImage() {
        return eventImage;
    }

    public String getYoutubeEnding() {
        return youtubeEnding;
    }
}
