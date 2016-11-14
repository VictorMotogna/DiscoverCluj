package com.pccosmin.inmemory;

import com.pccosmin.testy.infrastructure.TestyApplication;

/**
 * Created by CristianCosmin on 10.11.2016.
 */

public class Module {
    public static void Register(TestyApplication application){
        new InMemoryLocationService(application);
        new InMemoryCardsService(application);
        new InMemoryRushEventsService(application);
    }
}
