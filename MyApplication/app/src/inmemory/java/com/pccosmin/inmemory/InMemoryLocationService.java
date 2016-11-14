package com.pccosmin.inmemory;

import android.widget.EditText;
import android.widget.TextView;

import com.pccosmin.testy.R;
import com.pccosmin.testy.activities.AddNewLocationActivity;
import com.pccosmin.testy.infrastructure.TestyApplication;
//import com.pccosmin.testy.model.Brother;
import com.pccosmin.testy.model.Location;
//import com.pccosmin.testy.services.BrotherServices;
import com.pccosmin.testy.services.LocationServices;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CristianCosmin on 10.11.2016.
 */

public class InMemoryLocationService extends BaseInMemory {

//    @BindView(R.id.editText)
//    EditText name;
//
//    @BindView(R.id.editText2)
//    EditText address;
//
//    @BindView(R.id.editText3)
//    EditText description;
//
//    @BindView(R.id.editText4)
//    EditText funFact;
//
//    @BindView(R.id.editText5)
//    EditText inauguration;

    public InMemoryLocationService(TestyApplication testyApplication) {
        super(testyApplication);
//        ButterKnife.bind(this);
    }

    @Subscribe
    public void getLocation(LocationServices.SearchLocationRequest request) {
        LocationServices.SearchLocationResponse response = new LocationServices.SearchLocationResponse();
        response.locations = new ArrayList<>();

        response.locations.add(new Location(
                2,
                "FSEGA",
                "Ştiinţe Economice şi Gestiunea Afacerilor",
                "http://clujazi.ro/wp-content/uploads/fsega-cluj1.jpg",
                "Teodor Mihali Street",
                "18 July 2007",
                "Number 1 in Romania, 2007",
                46.773034,
                23.620588
        ));


        response.locations.add(new Location(
                6,
                "Cluj Arena",
                "Modern architecture sports arena",
                "https://thearchandthedome.files.wordpress.com/2013/04/cluj_arena_01.jpg",
                "Stadium Alley",
                "October 2011",
                "Capacity = 30,000+",
                46.771649,
                23.587243
        ));


        response.locations.add(new Location(
                3,
                "Piata Unirii",
                "Biserica Sfantul Mihail & Matei Corvin Statue",
                "http://actualdecluj.ro/wp-content/uploads/2015/06/piata-unirii-2.jpg",
                "Iuliu Maniu Street",
                "Works started 1316",
                "Church Tower - 80m",
                46.770071,
                23.59037
        ));
        response.locations.add(new Location(
                4,
                "Euphoria Biergarten",
                "Popular tasteful restaurant, great beer",
                "https://cluj.com/assets/euphoria.jpg",
                "Cardinal Iuliu Hossu Street",
                "Open from 8:00 a.m.",
                "Owned by Ursus company",
                46.768520,
                23.579573
        ));
        response.locations.add(new Location(
                5,
                "Piata Muzeului",
                "Obelisc, great places",
                "http://static.panoramio.com/photos/large/61489553.jpg",
                "Eile Zola Street",
                "Middle Ages",
                "Also named Karolina Squares",
                46.771649,
                23.587243
        ));

        response.locations.add(new Location(
                1,
                "Iulius Mall",
                "A shopping mall owned by Iulius Group",
                "http://worldfranchise.eu/sites/default/files/estates/photos/iulius_mall_cluj1_0.jpg",
                "Alexandru Vaida Voevod Street",
                "10 November 2007",
                "205+ stores",
                46.771947,
                23.625938
        ));

        bus.post(response);
    }
}
