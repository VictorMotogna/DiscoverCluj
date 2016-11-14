package com.pccosmin.inmemory;

import com.pccosmin.testy.infrastructure.TestyApplication;
import com.pccosmin.testy.model.RushEvent;
import com.pccosmin.testy.services.RushEventsService;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

public class InMemoryRushEventsService extends BaseInMemory {
    public InMemoryRushEventsService(TestyApplication testyApplication) {
        super(testyApplication);
    }

    @Subscribe
    public void searchRushEventsCommunity(RushEventsService.SearchRushEventsCommunityRequest request){
        RushEventsService.SearchRushEventsCommunityResponse response = new RushEventsService.SearchRushEventsCommunityResponse();
        response.communityRushEvents = new ArrayList<>();
        response.communityRushEvents.add(new RushEvent(
                1,
                "Rush Community Event 1",
                "11/11/2016",
                "8:00pm",
                "Ubb cluj",
                2.2,
                2.2,
                true,
                "Cool Event"
            ));
        bus.post(response);
    }

    @Subscribe
    public void searchRushEventsSocial(RushEventsService.SearchRushEventsSocialRequest request){
        RushEventsService.SearchRushEventsSocialResponse response = new RushEventsService.SearchRushEventsSocialResponse();
        response.socialRushEvents = new ArrayList<>();
        response.socialRushEvents.add(new RushEvent(
                2,
                "Rush Social Event 2",
                "11/11/2016",
                "10:00pm",
                "1 Mihail Kogălniceanu Street, Cluj-Napoca, Romania",
                46.767462,
                23.591655,
                false,
                "Cool Event"
        ));
        bus.post(response);
    }
}
