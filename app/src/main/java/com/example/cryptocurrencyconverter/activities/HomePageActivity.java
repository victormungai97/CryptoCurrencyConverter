package com.example.cryptocurrencyconverter.activities;

import android.support.v4.app.Fragment;

import com.example.cryptocurrencyconverter.fragments.HomePageFragment;

public class HomePageActivity extends MainFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new HomePageFragment();
    }
}
