package com.pccosmin.testy.infrastructure;

import android.app.Application;

import com.pccosmin.inmemory.Module;
import com.squareup.otto.Bus;

public class TestyApplication extends Application {
    private Bus bus;

    public TestyApplication() {
        bus = new Bus();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Module.Register(this);
    }

    public Bus getBus() {
        return bus;
    }
}
