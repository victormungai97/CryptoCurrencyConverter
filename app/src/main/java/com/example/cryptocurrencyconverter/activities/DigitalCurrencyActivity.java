package com.example.cryptocurrencyconverter.activities;

import android.support.v4.app.Fragment;

import com.example.cryptocurrencyconverter.R;
import com.example.cryptocurrencyconverter.fragments.DigitalCurrencyFragment;

public class DigitalCurrencyActivity extends MainFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return digitalCurrencyFragment();
    }

    private Fragment digitalCurrencyFragment() {
        String [] currency_titles = getResources().getStringArray(R.array.crypto_currencies);
        String [] currency_images = getResources().getStringArray(R.array.crypto_currencies_symbols);
        String [] currencies_list = getResources().getStringArray(R.array.crypto_currencies);
        String [] currencies_symbols = getResources().getStringArray(R.array.crypto_currencies_symbols);

        return DigitalCurrencyFragment.newInstance(currency_titles, currency_images,
                currencies_list, currencies_symbols);
    }
}
