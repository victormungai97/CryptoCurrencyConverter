package com.example.cryptocurrencyconverter.activities;

import android.support.v4.app.Fragment;
import com.example.cryptocurrencyconverter.fragments.CryptoCurrencyFragment;

public class CryptoCurrencyActivity extends MainFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CryptoCurrencyFragment();
    }
}
