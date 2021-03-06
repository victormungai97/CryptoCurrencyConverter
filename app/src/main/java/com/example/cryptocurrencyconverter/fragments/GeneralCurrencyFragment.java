package com.example.cryptocurrencyconverter.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cryptocurrencyconverter.R;
import com.example.cryptocurrencyconverter.others.Currency;
import com.example.cryptocurrencyconverter.others.CurrencyGridLayoutAdapter;
import com.example.cryptocurrencyconverter.others.Permissions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import static com.example.cryptocurrencyconverter.others.NetworkingClass.isNetworkAvailable;
import static com.example.cryptocurrencyconverter.others.Others.Constants.*;
import static com.example.cryptocurrencyconverter.others.Others.Currencies.*;

/**
 * Created by victor mungai on 11/4/17.
 * This class will be the base fragment for the various currency fragments
 * CREDITS
 * Card view: https://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/
 * Currency images: Google image search, https://icons8.com and Junik Studio
 * (https://www.behance.net/JunikStudio or http://www.junikstudio.com)
 */

public abstract class GeneralCurrencyFragment extends GeneralFragmentClass {
    static String cryptoSymbol;
    static String currency1;

    static List<Currency> currencies = new ArrayList<>();
    GridLayoutManager mLayoutManager;
    CurrencyGridLayoutAdapter currencyGridLayoutAdapter;
    RecyclerView recyclerViewMain;
    Parcelable mListState;
    CoordinatorLayout coordinatorLayout;
    Timer timer;
    int image_res;
    static String [] currency_titles = null;
    static String [] currency_codes = null;
    String [] currencies_list = null;
    String [] currencies_symbols = null;
    boolean isCryptoToCurrency;
    boolean isDigital;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // retrieve contents on screen before rotation
        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable(LIST_STATE_KEY);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_CURRENCY) {
            Log.e(TAG, "Received");
            updateBaseCurrency(data.getStringExtra(EXTRA_CURRENCY));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check permissions and request those not granted
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            Permissions.checkPermission(getActivity(), getActivity());
        }

        if (getArguments() != null) {
            currency_titles = getArguments().getStringArray(TITLES);
            currency_codes = getArguments().getStringArray(CODES);
            currencies_list = getArguments().getStringArray(LIST);
            currencies_symbols = getArguments().getStringArray(SYMBOLS);
            isCryptoToCurrency = getArguments().getBoolean(CRYPTO_TO_CURRENCY);
            isDigital = getArguments().getBoolean(DIGITAL_CURRENCY);
            image_res = getArguments().getInt(CRYPTO_SYMBOL);
            image_res = (image_res == 0) ? R.drawable.money_4 : image_res;
        }

        FragmentManager fm = getFragmentManager();
        BaseCurrencyDialogFragment dialog = BaseCurrencyDialogFragment.newInstance(currency_titles);
        // create connection btn fragments by making CryptoCurrencyFragment
        // the target of BaseCurrencyDialogFragment
        dialog.setTargetFragment(this, REQUEST_CURRENCY);
        assert fm != null;
        dialog.show(fm, null);
    }

    public void initializeWidgets() {
        recyclerViewMain = view.findViewById(R.id.card_recycler_view);
        coordinatorLayout = view.findViewById(R.id.fragment_coordinator_layout);

        updateBaseCurrency("");
        setAdapter();
        setLayoutManager();

        // check if last element reached
        currencyGridLayoutAdapter.setOnBottomReachedListener(this);

        if (!isNetworkAvailable(getContext())){
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "This app needs internet access", Snackbar.LENGTH_LONG)
                    .setAction("ENABLE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FragmentManager fm = getFragmentManager();
                            Permissions.InternetConnectionDialogFragment dialog = new Permissions.InternetConnectionDialogFragment();
                            assert fm != null;
                            dialog.show(fm, INTERNET_PICKER);
                        }
                    });

            // Changing message text color
            snackbar.setActionTextColor(getResources().getColor(R.color.colorSnackbarActionText));

            // Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }

        initializeCurrencies();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) timer.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        setAdapter();

        if (mListState != null) {
            mLayoutManager.onRestoreInstanceState(mListState);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // save recycler view state
        mListState = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, mListState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("INTERNET "+TAG, "Network connection -> " + isNetworkAvailable(getContext()));
    }

    public static String getCryptoSymbol() {
        return cryptoSymbol;
    }

    /**
     * Gets the images of currencies saved in drawable folder
     * and pass them to an instance of Currency class
     * which is then saved a list of Currencies
     */
    private void initializeCurrencies() {
        // clear currencies if it is not empty
        if (!currencies.isEmpty()) currencies.clear();

        // create instance of Currency class and add to list
        for (int i = 0; i < currencies_list.length; i++ ){
            if (isDigital) {
                currencies.add(new Currency(currencies_list[i], digital_currency_images[i], digital_icons[i],
                        currencies_symbols[i]));
            } else {
                currencies.add(new Currency(currencies_list[i], currency_images[i], icons[i],
                        currencies_symbols[i]));
            }
        }
        currencies.add(new Currency("Add new currency", currency_images[20]));
    }

    /**
     * Used to create or update the adapter for the recycler view
     */
    private void setAdapter() {
        if (currencyGridLayoutAdapter == null) {
            timer = new Timer();
            currencyGridLayoutAdapter = new CurrencyGridLayoutAdapter(getActivity(), currencies, timer);
            recyclerViewMain.setItemAnimator(new DefaultItemAnimator());
            recyclerViewMain.setAdapter(currencyGridLayoutAdapter);
            recyclerViewMain.invalidate();
        } else {
            currencyGridLayoutAdapter.setCurrencies(currencies);
            currencyGridLayoutAdapter.notifyDataSetChanged();
        }
    }

    public abstract void setLayoutManager();

    /*
     * Update the base cryptocurrency symbol and image based on choice selected by user from spinner
     */
    private void updateBaseCurrency(String currency) {
        // set base currency
        currency1 = currency;

        // change base currency symbol and image based on choice
        for ( int i = 0; i < currency_titles.length; i++ ){
            if (currency1.equals(currency_titles[i])) {
                cryptoSymbol = currency_codes[i];
                Context context = getContext();
                assert context != null;
                if (isCryptoToCurrency)
                    image_res = getResources().getIdentifier(currency.toLowerCase(), "drawable",
                            context.getPackageName());
                break;
            }
        }

        // set image for crypto currency to image view in collapsing toolbar
        Picasso.with(getContext())
                .load(image_res)
                .into((ImageView) view.findViewById(R.id.backdrop));
        Log.e(TAG, "Symbol -> " + cryptoSymbol);
    }
}
