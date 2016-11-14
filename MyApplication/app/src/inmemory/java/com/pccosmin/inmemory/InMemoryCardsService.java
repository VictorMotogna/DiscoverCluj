package com.pccosmin.inmemory;

import com.pccosmin.testy.infrastructure.TestyApplication;
import com.pccosmin.testy.model.EventCard;
import com.pccosmin.testy.services.EventCardService;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

public class InMemoryCardsService extends BaseInMemory {
    public InMemoryCardsService(TestyApplication testyApplication) {
        super(testyApplication);
    }

    @Subscribe
    public void searchCommunityCards(EventCardService.SearchCommunityServiceCardsRequest request){
        EventCardService.SearchCommunityServiceCardsResponse response = new EventCardService.SearchCommunityServiceCardsResponse();
        response.communityServiceCards = new ArrayList<>();

        response.communityServiceCards.add(new EventCard(
                "Duminici in familie",
                1,
                "Spend your Sundays with your family!",
                "http://www.clujlife.com/wp/wp-content/uploads/2016/11/duminici.jpg",
                false,
                "null"
        ));

        response.communityServiceCards.add(new EventCard(
                "Save Santa Claus",
                2,
                "X-mas is almost here and we need to make sure Santa is fine.",
                "http://pngimg.com/upload/small/santa_claus_PNG9992.png",
                true,
                "null"
        ));

        bus.post(response);
    }

    @Subscribe
    public void searchBrotherhoodCards(EventCardService.SearchBrotherhoodCardsRequest request){
        EventCardService.SearchBrotherhoodCardsResponse response = new EventCardService.SearchBrotherhoodCardsResponse();
        response.brotherhoodCards = new ArrayList<>();

        response.brotherhoodCards.add(new EventCard(
                "UNTOLD",
                3,
                "The biggest music festival in Romania.",
                "http://tb.ziareromania.ro/Vrei-sa-mergi-la-Untold----cum-poti-beneficia-de-reduceri-sau-gratuitati/c8c2c300ec9c86d6f7/327/0/1/70/Vrei-sa-mergi-la-Untold----cum-poti-beneficia-de-reduceri-sau-gratuitati.jpg",
                false,
                "null"
        ));

        response.brotherhoodCards.add(new EventCard(
                "Electric Castle",
                4,
                "Unique music festival, near the beautiful area of Banffy Castle.",
                "https://electriccastle.ro/assets/img/ec-official-logo.jpg",
                true,
                "null"
        ));

        response.brotherhoodCards.add(new EventCard(
                "Jazz in the Park",
                5,
                "Jazz music, hammocks and joy. All of them in the park.",
                "https://cluj-wpengine.netdna-ssl.com/assets/12552876_1046948068661174_7890728793376797755_n-372x225.jpg",
                true,
                null
        ));

        bus.post(response);
    }

    @Subscribe
    public void searchSocialCards(EventCardService.SearchSocialCardsRequest request){
        EventCardService.SearchSocialCardsResponse response = new EventCardService.SearchSocialCardsResponse();
        response.socialCards = new ArrayList<>();

        response.socialCards.add(new EventCard(
                "Startup Networking",
                5,
                "Get to network with other brave entrepreneurs.",
                "http://startupireland.ie/wp-content/uploads/2015/01/Silicon-Drinkabout-New-Feature-642x336.jpg",
                false,
                "null"
        ));
//
//        response.socialCards.add(new EventCard(
//                "Social Event 2",
//                6,
//                "Social Event 2's description",++-*
//                "http://www.gravatar.com/avatar/6?d=identicon",
//                true,
//                "null"
//        ));

        bus.post(response);
    }

//    public void deleteEvent() {
//        EventCardService.SearchBrotherhoodCardsRequest brotherhoodCardsRequest = new EventCardService.SearchBrotherhoodCardsRequest();
//    }
}
