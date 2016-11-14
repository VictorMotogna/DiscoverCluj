package com.pccosmin.testy.views.RushViews;

import com.pccosmin.testy.model.RushEvent;

import java.util.List;

public class Item {
    public int type;
    public String test;
    public RushEvent rushEvent;
    public List<Item> invisibleChildren;

    public Item(int type, RushEvent rushEvent) {
        this.type = type;
        this.rushEvent = rushEvent;
    }

    public Item(int type, String test) {
        this.type = type;
        this.test = test;
    }
}
