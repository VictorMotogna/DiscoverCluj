package com.pccosmin.testy.services;

import com.pccosmin.testy.model.Location;

import java.util.List;

public class LocationServices {

    public LocationServices(){}

    public static class SearchLocationRequest{
        public String fireBaseUrl;

        public SearchLocationRequest(String fireBaseUrl) {
            this.fireBaseUrl = fireBaseUrl;
        }
    }

    public static class SearchLocationResponse{
        public List<Location> locations;
    }
}
