package com.example.cryptocurrencyconverter.activities;

import android.support.v4.app.Fragment;

import com.example.cryptocurrencyconverter.R;
import com.example.cryptocurrencyconverter.fragments.ConventionalCurrencyFragment;

public class ConventionalCurrencyActivity extends MainFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return conventionalCurrencyFragment();
    }

    private Fragment conventionalCurrencyFragment() {
        String [] currency_titles = getResources().getStringArray(R.array.currencies);
        String [] currency_images = getResources().getStringArray(R.array.currencies_symbols);
        String [] currencies_list = getResources().getStringArray(R.array.currencies);
        String [] currencies_symbols = getResources().getStringArray(R.array.currencies_symbols);

        return ConventionalCurrencyFragment.newInstance(currency_titles, currency_images,
                currencies_list, currencies_symbols);
    }
}
