package com.example.cryptocurrencyconverter.fragments;

import android.widget.ImageView;

import com.example.cryptocurrencyconverter.R;
import com.squareup.picasso.Picasso;

/**
 * Created by victor on 11/5/17.
 * This class will serve as the home page
 */

public class HomePageFragment extends GeneralFragmentClass{
    @Override
    public void initializeWidgets() {
        // set image for crypto currency to image view in collapsing toolbar
        Picasso.with(getContext())
                .load(R.mipmap.ic_greedy_emoji)
                .into((ImageView) view.findViewById(R.id.backdrop));
    }
}
