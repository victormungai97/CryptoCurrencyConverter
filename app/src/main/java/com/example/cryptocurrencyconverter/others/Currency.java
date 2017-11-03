package com.example.cryptocurrencyconverter.others;

import com.example.cryptocurrencyconverter.R;

/**
 * Created by victor mungai on 10/27/17.
 * This class models a currency with details of currency eg its name
 */

public class Currency {

    private String name;
    private int thumbnail;
    private int icon;
    private String ID;
    private String exchange;

    public Currency(String name, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.icon = R.drawable.icons_general_note;
        this.ID = "";
    }

    public Currency(String name, int thumbnail, int icon, String ID) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.icon = icon;
        this.exchange = null;
        this.ID = ID;
    }

    String getName() {
        return name;
    }

    int getThumbnail() {
        return thumbnail;
    }

    int getIcon() {
        return icon;
    }

    String getExchange() {
        return exchange;
    }

    String getID() {
        return ID;
    }

}
