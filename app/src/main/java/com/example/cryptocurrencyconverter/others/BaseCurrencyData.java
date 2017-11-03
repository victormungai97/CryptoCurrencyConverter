package com.example.cryptocurrencyconverter.others;

/**
 * Created by victor mungai on 10/28/17.
 * This class is used to details of base currency
 */

public class BaseCurrencyData {

    private String text;
    private int imageId;

    public BaseCurrencyData(String text, int imageId){
        this.text = text;
        this.imageId = imageId;
    }

    public String getText(){
        return text;
    }

    int getImageId(){
        return imageId;
    }
}