package com.pccosmin.testy.services;

import com.pccosmin.testy.model.RushEvent;

import java.util.List;

public class RushEventsService {
    private RushEventsService(){}

    public static class SearchRushEventsCommunityRequest{
        public String fireBaseUrl;

        public SearchRushEventsCommunityRequest(String fireBaseUrl) {
            this.fireBaseUrl = fireBaseUrl;
        }
    }

    public static class SearchRushEventsCommunityResponse{
        public List<RushEvent> communityRushEvents;
    }

    public static class SearchRushEventsSocialRequest{
        public String fireBaseUrl;

        public SearchRushEventsSocialRequest(String fireBaseUrl) {
            this.fireBaseUrl = fireBaseUrl;
        }
    }

    public static class SearchRushEventsSocialResponse{
        public List<RushEvent> socialRushEvents;
    }
}
