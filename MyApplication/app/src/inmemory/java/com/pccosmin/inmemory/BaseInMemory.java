package com.pccosmin.inmemory;

import com.pccosmin.testy.infrastructure.TestyApplication;
import com.squareup.otto.Bus;

public class BaseInMemory {
    protected final Bus bus;
    protected  final TestyApplication testyApplication;

    public BaseInMemory(TestyApplication testyApplication) {
        this.bus = testyApplication.getBus();
        this.testyApplication = testyApplication;
        bus.register(this);
    }
}
