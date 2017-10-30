package com.example.cryptocurrencyconverter.others;

import com.example.cryptocurrencyconverter.R;

/**
 * Created by victor on 10/27/17.
 * This class models a currency with details of currency eg its name
 */

public class Currency {

    private String name;
    private int thumbnail;
    private int icon;
    private String exchange;

    public Currency(String name, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.icon = R.drawable.icons_general_note;
    }

    public Currency(String name, int thumbnail, int icon) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.icon = icon;
    }

    public Currency(String name, int thumbnail, int icon, String exchange) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.icon = icon;
        this.exchange = exchange;
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

    /* public void setExchange(String exchange) {
        this.exchange = exchange;
    } */
}
