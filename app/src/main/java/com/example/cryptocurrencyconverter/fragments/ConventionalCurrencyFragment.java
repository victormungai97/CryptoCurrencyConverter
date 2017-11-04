package com.example.cryptocurrencyconverter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.example.cryptocurrencyconverter.R;
import com.example.cryptocurrencyconverter.activities.ConversionActivity;
import com.example.cryptocurrencyconverter.others.CurrencyGridLayoutAdapter;

import static com.example.cryptocurrencyconverter.others.Others.Constants.*;

/**
 * Created by victor mungai on 11/4/17.
 * This fragment will inflate the layout for conversion between conventional currencies
 */

public class ConventionalCurrencyFragment extends GeneralCurrencyFragment {

    public static ConventionalCurrencyFragment newInstance(String[] titles, String[] codes,
                                                     String[] list, String[] symbols) {

        Bundle args = new Bundle();
        args.putStringArray(TITLES, titles);
        args.putStringArray(CODES, codes);
        args.putStringArray(LIST, list);
        args.putStringArray(SYMBOLS, symbols);
        args.putBoolean(CRYPTO_TO_CURRENCY, false);
        args.putInt(CRYPTO_SYMBOL, R.drawable.icons_currency);

        ConventionalCurrencyFragment fragment = new ConventionalCurrencyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setLayoutManager() {
        recyclerViewMain.setHasFixedSize(true); // tell RecyclerView that size will remain constant
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewMain.setLayoutManager(mLayoutManager);

        currencyGridLayoutAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RecyclerView.ViewHolder holder = recyclerViewMain.findViewHolderForAdapterPosition(i);
                CurrencyGridLayoutAdapter.Holder myHolder = (CurrencyGridLayoutAdapter.Holder) holder;
                String currency2 = myHolder.getCurrency_title().getText().toString(),
                        exchange_rate = myHolder.getCurrency_exchange().getText().toString(),
                        currSymbol = currencies_symbols[i];
                Intent intent = ConversionActivity.newIntent(getContext(), currency1, currency2,
                        exchange_rate, cryptoSymbol, currSymbol, currency_titles, currency_codes);
                if (intent != null) startActivity(intent);
            }
        });
        currencyGridLayoutAdapter.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });
    }
}
