package com.example.cryptocurrencyconverter.others;

/**
 * Created by victor mungai on 10/30/17.
 * This interface is used to detect when last element in recycler view has been reached
 * Courtesy of https://medium.com/@ayhamorfali/
 */

public interface OnBottomReachedListener {

    void onBottomReached(int position);

    void onBottomNotReached(int position);
}
